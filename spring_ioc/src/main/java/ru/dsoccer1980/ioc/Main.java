package ru.dsoccer1980.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

  public static void main(String[] args) {
//    ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//    System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    Quoter quoter = context.getBean(Quoter.class);
    quoter.sayQoute();

//    SingletonBean singletonBean = context.getBean(SingletonBean.class);
//    singletonBean.getPrototypeBean();
//    singletonBean.getPrototypeBean();
//    SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
//    singletonBean2.getPrototypeBean();
//    singletonBean2.getPrototypeBean();

  }


}
