package com.hanma56.mongodb.service;

import com.hanma56.mongodb.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author catface
 * @date 2019-04-07
 */
public interface StudentRpService extends MongoRepository<Student, String> {

  /**
   * 分页查询
   */
  Page<Student> findByName(@Param("name") String studentName, Pageable pageable);

  /**
   * 模糊查询 姓名
   */
  @Query("{'name':{$regex:'^.*?0.*$'},'age':?1}")
  Page<Student> findByNameLike(String name, Integer age, Pageable pageable);


  /**
   * 模糊查询 姓名 -1 desc , 1 asc
   */
  @Query(value = "{'name':{$regex:'^.*?0.*$'},'age':?1}",sort = "{ created : -1 }")
  Page<Student> findByNameLikeSortByCreated(String name, Integer age, Pageable pageable);

}
