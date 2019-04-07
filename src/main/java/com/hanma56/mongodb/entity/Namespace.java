package com.hanma56.mongodb.entity;

import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author catface
 * @date 2019-04-06
 */
@Data
@Document(collection = "tes_namespace")
public class Namespace {

  @Id
  private String id;

  private String name;

  private String code;

  private String description;

  private Date created;

  private Date updated;

  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String CODE = "code";
  public static final String DESCRIPTION = "description";
  public static final String CREATED = "created";
  public static final String UPDATED = "updated";

}
