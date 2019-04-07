package com.hanma56.mongodb;

import com.hanma56.mongodb.entity.Course;
import com.hanma56.mongodb.service.CourseRpService;
import java.util.Date;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author catface
 * @date 2019-04-07
 */
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
  public void increment(){
    Course course = new Course();
    course.setId(12L);
    // 整个文档更新
    courseRpService.save(course);
    System.out.println(course);
  }

}
