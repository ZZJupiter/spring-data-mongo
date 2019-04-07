package com.hanma56.mongodb.service;

import com.hanma56.mongodb.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author catface
 * @date 2019-04-07
 */
public interface CourseRpService extends MongoRepository<Course,Long> {

}
