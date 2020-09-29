package al.ikubinfo.healthometer.activity.mappers;

import al.ikubinfo.commons.mapper.DtoToEntityBidirectionalMapper;
import al.ikubinfo.healthometer.activity.dto.FoodDTO;
import al.ikubinfo.healthometer.activity.entity.Food;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FoodMapper extends DtoToEntityBidirectionalMapper<FoodDTO, Food> {

  FoodDTO toDto(Food food);

  @Override
  @InheritInverseConfiguration
  Food toEntity(FoodDTO foodDTO);
}
