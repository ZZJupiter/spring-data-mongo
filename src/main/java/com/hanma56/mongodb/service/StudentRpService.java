package com.hanma56.mongodb.service;

import com.hanma56.mongodb.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author catface
 * @date 2019-04-07
 */
public interface StudentRpService extends MongoRepository<Student, String> {

}
