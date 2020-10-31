package ru.dsoccer1980.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProfilingBeanPostProcessor implements BeanPostProcessor {

  private Map<String, Class> map = new HashMap<>();

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    Profiling profiling = bean.getClass().getDeclaredAnnotation(Profiling.class);
    if (profiling != null) {
      map.put(beanName, bean.getClass());
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    Class profilingClass = map.get(beanName);
    if (profilingClass != null) {
      System.out.println("BeanPostProcessor + postProcessAfterInitialization");
      return Proxy.newProxyInstance(profilingClass.getClassLoader(), profilingClass.getInterfaces(), new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          long begin = System.nanoTime();
          Object invoke = method.invoke(bean, args);
          System.out.println("Time:" + (System.nanoTime() - begin));
          return invoke;
        }
      });
    }
    return bean;
  }
}
