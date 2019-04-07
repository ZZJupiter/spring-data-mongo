package com.hanma56.mongodb.service;

import com.hanma56.mongodb.entity.Namespace;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author catface
 * @date 2019-04-06
 */
@Service
public class NamespaceRpService {

  @Autowired
  private MongoTemplate mongoTemplate;


  /**
   * 保存文档
   */
  public void save(Namespace namespace) {
    mongoTemplate.save(namespace);
  }

  /**
   * 批量保存
   */
  public void saveBatch(List<Namespace> namespaces) {
    BulkOperations operations = mongoTemplate.bulkOps(BulkMode.UNORDERED, Namespace.class);
    operations.insert(namespaces);
    operations.execute();
  }

  /**
   * find by id
   */
  public Namespace queryById(String id) {
    return mongoTemplate.findById(id, Namespace.class);
  }

  /**
   * 分页查询
   *
   * @param name 名称
   * @param code 编码
   * @param current 当前页码
   * @param size 分页大小
   */
  public Page<Namespace> queryByCode(String name, String code, Long current, Long size) {
    Query query = new Query();
    if (StringUtils.isNotEmpty(name)) {
      query.addCriteria(Criteria.where(Namespace.NAME).is(name).regex("^.*" + name + ".*$"));
    }
    if (StringUtils.isNotEmpty(code)) {
      query.addCriteria(Criteria.where(Namespace.CODE).is(code).regex("^.*" + code + ".*$"));
    }
    Sort sort = new Sort(Direction.DESC, Namespace.UPDATED);
    PageRequest pageRequest = PageRequest.of(current.intValue(), size.intValue(), sort);
    query.with(pageRequest);
    long total = mongoTemplate.count(query, Namespace.class);
    List<Namespace> namespaces = mongoTemplate.find(query, Namespace.class);
    return new PageImpl<>(namespaces, pageRequest, total);
  }


}
