package com.hanma56.mongodb.config;

/**
 * @author catface
 * @date 2019-04-07
 */

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.lang.annotation.Annotation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


@Component
public class SaveEventListener extends AbstractMongoEventListener<MongoId> {


  @Override
  public void onBeforeConvert(BeforeConvertEvent<MongoId> event) {
    final Object source = event.getSource();
    if (source != null) {
      ReflectionUtils.doWithFields(MongoId.class, field -> {
        ReflectionUtils.makeAccessible(field);
        Annotation[] annotations = field.getAnnotations();
        if (annotations == null || annotations.length <= 0) {
          return;
        }
        for (Annotation annotation : annotations) {
          if (annotation.annotationType().equals(Id.class)) {
            if (field.getType().equals(Long.class)) {
              Object value = field.get(source);
              if (value == null) {
                field.set(source, IdWorker.getId());
              }
            }
          }
        }
      });
    }
  }


}
