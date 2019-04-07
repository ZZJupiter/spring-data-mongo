package com.hanma56.mongodb;

import com.hanma56.mongodb.entity.Student;
import com.hanma56.mongodb.service.StudentRpService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author catface
 * @date 2019-04-07
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRpServiceTest {

  @Autowired
  private StudentRpService studentRpService;

  @Test
  public void save() {
    Student student = new Student();
    student.setName("zz");
    student.setAge(12);
    student.setCreated(new Date());
    student.setUpdated(new Date());
    studentRpService.save(student);
    System.out.println(student);
  }

  @Test
  public void saveBatch() {
    Student student = new Student();
    student.setName("zz");
    student.setAge(12);
    student.setCreated(new Date());
    student.setUpdated(new Date());
    List<Student> students = new ArrayList<>(100);
    for (int i = 0; i < 100; i++) {
      Student temp = new Student();
      BeanUtils.copyProperties(student, temp);
      students.add(temp);
    }
    studentRpService.saveAll(students);
    System.out.println(students);
  }

  @Test
  public void findAll() {
    List<Student> students = studentRpService.findAll();
    System.out.println(students);
  }

  @Test
  public void findByName() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    Page<Student> page = studentRpService.findByName("^.*z.*$", pageRequest);
    long total = page.getTotalElements();
    long pages = page.getTotalPages();
    System.out.println(total);
    System.out.println(pages);
    System.out.println(page);
  }

  @Test
  public void findByNameLike() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    Page<Student> page = studentRpService.findByNameLike("z", 12, pageRequest);
    long total = page.getTotalElements();
    long pages = page.getTotalPages();
    System.out.println(total);
    System.out.println(pages);
    System.out.println(page);
  }

  @Test
  public void findByNameLikeNullName() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    Page<Student> page = studentRpService.findByNameLike(null, 12, pageRequest);
    long total = page.getTotalElements();
    long pages = page.getTotalPages();
    System.out.println(total);
    System.out.println(pages);
    System.out.println(page);
  }

  @Test
  public void findByNameLikeNullAge() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    Page<Student> page = studentRpService.findByNameLike("zz", null, pageRequest);
    long total = page.getTotalElements();
    long pages = page.getTotalPages();
    System.out.println(total);
    System.out.println(pages);
    System.out.println(page);
  }

  @Test
  public void findByNameLikeSortByCreated() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    Page<Student> page = studentRpService.findByNameLikeSortByCreated("zz", 12, pageRequest);
    long total = page.getTotalElements();
    long pages = page.getTotalPages();
    System.out.println(total);
    System.out.println(pages);
    System.out.println(page);
  }

  @Test
  public void deleteAll() {
    studentRpService.deleteAll();
    System.out.println(11);
  }

  @Test
  public void generateStudent() {
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 100; j++) {
        List<Student> students = new ArrayList<>(100);
        for (int k = 0; k < 100; k++) {
          Student student = new Student();
          student.setName("小明" + i * j * k);
          Date currentDate = new Date();
          student.setUpdated(currentDate);
          student.setCreated(currentDate);
          student.setInstId((long) i * 10000 + j * 100 + k);
          student.setPhone("177" + i * 10000 + j * 100 + k);
          students.add(student);
        }
        studentRpService.saveAll(students);
        log.info("第i:{},j:{} 批次保存成功", i, j);
      }
    }
  }

  @Test
  public void findByIds(){
    Set<Long> ids = new HashSet<>();
    ids.add(1114829674417958913L);
    ids.add(1114839996369453110L);
    ids.add(1114839996369453123L);
    Iterable<Student> students = studentRpService.findAllById(ids);
    List<Student> students1 = studentRpService.findByIds(ids);
    System.out.println(students);
  }

}
