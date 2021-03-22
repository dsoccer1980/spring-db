package ru.dsoccer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dsoccer.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
