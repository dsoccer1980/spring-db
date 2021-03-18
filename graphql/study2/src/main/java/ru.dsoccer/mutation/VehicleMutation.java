package ru.dsoccer.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dsoccer.model.Vehicle;
import ru.dsoccer.service.VehicleService;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

  @Autowired
  private VehicleService vehicleService;

  Logger logger = LoggerFactory.getLogger(VehicleMutation.class);

  public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
    logger.debug("my mutation");
    return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
  }
}
