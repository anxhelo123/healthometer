package al.ikubinfo.healthometer.activity.dto;

import al.ikubinfo.commons.dto.BaseDto;
import al.ikubinfo.healthometer.activity.entity.Food;
import al.ikubinfo.healthometer.activity.entity.PhysicalActivity;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDTO extends BaseDto {

  private Date todayDate;
  private List<Food> food;
  private int waterQuantityInMl;
  private List<PhysicalActivity> physicalActivity;
}
