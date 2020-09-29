package al.ikubinfo.healthometer.activity.service;

import al.ikubinfo.healthometer.activity.dto.FoodDTO;
import al.ikubinfo.healthometer.activity.entity.Food;

public interface FoodService {
  Food insertFood(FoodDTO food);

  Food deleteFood(FoodDTO food);
}
