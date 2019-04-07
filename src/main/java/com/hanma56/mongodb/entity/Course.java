package com.hanma56.mongodb.entity;

import com.hanma56.mongodb.config.MongoId;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Version;

/**
 * @author catface
 * @date 2019-04-07
 */
@Data
public class Course extends MongoId {

  /**
   * 课程名称
   */
  private String courseName;

  /**
   * 课程容量
   */
  private Long capacity;

  /**
   * 创建时间
   */
  private Date created;

  /**
   * 更新时间
   */
  private Date updated;

  /**
   * 版本号,实现乐观锁
   */
  @Version
  private Long version;

  public static final String COURSE_NAME = "courseName";
  public static final String CAPACITY = "capacity";
  public static final String CREATED = "created";
  public static final String UPDATED = "updated";
  public static final String VERSION = "version";


}
