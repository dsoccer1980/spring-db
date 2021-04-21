package ru.dsoccer.corona;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import ru.dsoccer.corona.doctor.DefaultЦелитель;
import ru.dsoccer.corona.doctor.Целитель;

@Service
public class HospitalImpl implements Hospital {

//  @Autowired
//  @Qualifier("целительMap")
  private Map<String, Целитель> целительMap = new HashMap<>();

  @Override
  public void register(String type, Целитель целитель) {
    целительMap.put(type, целитель);
  }

  @Override
  public void process(Patient patient) {
    Целитель целитель = целительMap.getOrDefault(patient.getMethod(), new DefaultЦелитель());
    целитель.исцелять(patient);
  }


}
