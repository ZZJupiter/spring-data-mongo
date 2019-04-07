package com.hanma56.mongodb;

import com.hanma56.mongodb.entity.Student;
import com.hanma56.mongodb.entity.StudentRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author catface
 * @date 2019-04-07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

  @Autowired
  private StudentRepository studentRepository;

  @Test
  public void save() {
    Student student = new Student();
    student.setName("zz");
    student.setAge(12);
    student.setCreated(new Date());
    student.setUpdated(new Date());
    studentRepository.save(student);
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
      BeanUtils.copyProperties(student,temp);
      students.add(temp);
    }
    studentRepository.saveAll(students);
    System.out.println(students);
  }

  @Test
  public void findAll() {
    List<Student> students = studentRepository.findAll();
    System.out.println(students);
  }

}
