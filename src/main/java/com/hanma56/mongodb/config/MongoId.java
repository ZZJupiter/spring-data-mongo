package com.hanma56.mongodb.config;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author catface
 * @date 2019-04-07
 */
@Data
public class MongoId {

  @Id
  private Long id;

}
