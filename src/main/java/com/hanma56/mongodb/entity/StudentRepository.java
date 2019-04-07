package com.hanma56.mongodb.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author catface
 * @date 2019-04-07
 */
public interface StudentRepository extends MongoRepository<Student, String> {

}
