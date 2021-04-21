package ru.dsoccer1980.resourcetest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ResourceTest.class})
@ComponentScan(lazyInit = true)
@PropertySource("classpath:application.properties")
class ResourceTest {

  @Value("${author.defaultpath}")
  private Resource resource;

  @Test
  void test() {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
      List<String> resultList = reader.lines().collect(Collectors.toList());
      assertThat(resultList).isEqualTo(Arrays.asList("mytest1", "mytest2", "mytest3"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
