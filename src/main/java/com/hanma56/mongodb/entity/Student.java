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

  private String name;

  private Integer age;

  private Date created;

  private Date updated;

}
