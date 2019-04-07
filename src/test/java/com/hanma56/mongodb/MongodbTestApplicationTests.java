package com.hanma56.mongodb;

import com.hanma56.mongodb.entity.Namespace;
import com.hanma56.mongodb.service.NamespaceRpService;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTestApplicationTests {

  @Autowired
  private NamespaceRpService namespaceRpService;

  @Autowired
  private MongoClient mongoClient;

  @Test
  public void saveDocument() {
    Namespace namespace = new Namespace();
    namespace.setCode("code");
    namespace.setDescription("description");
    namespace.setName("name");
    namespace.setCreated(new Date());
    namespace.setUpdated(new Date());
    List<Namespace> namespaces = new ArrayList<>(100);
    for(int i=0;i<100;i++){
      Namespace temp = new Namespace();
      BeanUtils.copyProperties(namespace,temp);
      temp.setCode(temp.getCode()+i);
      namespaces.add(temp);
    }
    namespaceRpService.saveBatch(namespaces);
    namespaceRpService.save(namespace);
    System.out.println(namespace);
    Namespace namespaceSaved = namespaceRpService.queryById(namespace.getId());
    System.out.println(namespaceSaved);
    Page<Namespace> page = namespaceRpService.queryByCode("name", "1", 1L, 3L);
    System.out.println(page);
  }

}
