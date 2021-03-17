package ru.dsoccer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsoccer.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
