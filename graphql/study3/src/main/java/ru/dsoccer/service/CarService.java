package ru.dsoccer.service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsoccer.model.Car;
import ru.dsoccer.repository.CarRepository;

@Service
@GraphQLApi
@RequiredArgsConstructor
public class CarService {

  private final CarRepository carRepository;

  @GraphQLQuery(name = "getCars")
  public List<Car> findAll() {
    return carRepository.findAll();
  }

  @GraphQLMutation(name = "saveCar")
  public Car save(@GraphQLArgument(name = "car") Car car) {
    return carRepository.save(car);
  }

}
