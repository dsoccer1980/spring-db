package ru.dsoccer.corona.configuration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InjectListAnnotationBPP implements BeanPostProcessor {

  @Autowired
  private ApplicationContext context;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    Field[] fields = bean.getClass().getDeclaredFields();
    for (Field field : fields) {
      InjectList annotation = field.getAnnotation(InjectList.class);
      if (annotation != null) {
        field.setAccessible(true);
        Class<?>[] classes = annotation.value();
        List<Object> list = Arrays.stream(classes).map(aClass -> context.getBean(aClass)).collect(Collectors.toList());
        try {
          field.set(bean, list);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return bean;
  }


}
