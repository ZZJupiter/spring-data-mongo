package com.hanma56.mongodb.entity;

import com.hanma56.mongodb.config.MongoId;
import java.util.Date;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author catface
 * @date 2019-04-07
 */
@Data
@Document(collection = "student")
public class Student extends MongoId {

  /**
   * 机构ID
   */
  private Long instId;

  /**
   * 学生姓名
   */
  private String name;

  /**
   * 学生年龄
   */
  private Integer age;

  /**
   * 预留手机号
   */
  private String phone;

  /**
   * 创建时间
   */
  private Date created;

  /**
   * 更新时间
   */
  private Date updated;

}
