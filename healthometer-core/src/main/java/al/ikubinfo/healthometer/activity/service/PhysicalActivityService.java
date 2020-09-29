package al.ikubinfo.healthometer.activity.service;

import al.ikubinfo.healthometer.activity.dto.PhysicalActivityDTO;
import al.ikubinfo.healthometer.activity.entity.PhysicalActivity;

public interface PhysicalActivityService {

  PhysicalActivity insertPhysicalActivity(PhysicalActivityDTO physicalActivity);

  PhysicalActivity deletePhysicalActivity(PhysicalActivityDTO physicalActivity);
}
