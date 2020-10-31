package ru.dsoccer1980.ioc;

import java.lang.reflect.Field;
import java.util.Random;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    Field[] fields = bean.getClass().getDeclaredFields();
    for (Field field : fields) {
      InjectRandomInt randomInt = field.getAnnotation(InjectRandomInt.class);
      if (randomInt != null) {
        System.out.println("BeanPostProcessor + postProcessBeforeInitialization");
        int max = randomInt.max();
        int min = randomInt.min();
        int count = min + new Random().nextInt(max - min);
        field.setAccessible(true);
        ReflectionUtils.setField(field, bean, count);
      }
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }
}
