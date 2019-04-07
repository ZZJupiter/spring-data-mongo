package com.hanma56.mongodb;

import com.hanma56.mongodb.entity.Course;
import com.hanma56.mongodb.entity.Namespace;
import com.hanma56.mongodb.service.CourseRpService;
import java.util.Date;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author catface
 * @date 2019-04-07
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRpServiceTest {

  @Autowired
  private CourseRpService courseRpService;

  @Test
  public void save(){
    Course course = new Course();
    course.setId(12L);
    course.setCapacity(1L);
    course.setCourseName("疯狂英语s");
    course.setCreated(new Date());
    course.setUpdated(new Date());
    courseRpService.save(course);
    System.out.println(course);
  }


  @Test
  public void update(){
    Optional<Course> optionalCourse = courseRpService.findById(12L);
    if (optionalCourse.isPresent()){
      Course course = optionalCourse.get();
      // 先查询,后修改,使用乐观锁
      courseRpService.save(course);
      System.out.println(course);
    }
  }

  @Test
  public void updateFail(){
    Optional<Course> optionalCourse = courseRpService.findById(12L);
    if (optionalCourse.isPresent()){
      Course course = optionalCourse.get();
      course.setVersion(0L);
      // 先查询,后修改,使用乐观锁
      try {
        courseRpService.save(course);
      }catch (OptimisticLockingFailureException e){
        log.error("版本号过低!",e);
      }
      System.out.println(course);
    }
  }

  @Test
  public void updateAll(){
    Course course = new Course();
    course.setId(12L);
    // 整个文档更新
    courseRpService.save(course);
    System.out.println(course);
  }


  @Test
  public void queryOnePage(){
    PageRequest pageRequest = PageRequest.of(0, 10);
    // mongo 分页,从第0页开始
    Page<Course> page = courseRpService.findAll(pageRequest);
    System.out.println(page);
  }

}
