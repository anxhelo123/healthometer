package al.ikubinfo.healthometer.activity.dto;

import al.ikubinfo.commons.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO extends BaseDto {

  private String name;

  private int caloriesGained;
}
