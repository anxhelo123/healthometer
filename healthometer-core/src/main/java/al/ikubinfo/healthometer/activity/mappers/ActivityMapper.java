package al.ikubinfo.healthometer.activity.mappers;

import al.ikubinfo.commons.mapper.DtoToEntityBidirectionalMapper;
import al.ikubinfo.healthometer.activity.dto.ActivityDTO;
import al.ikubinfo.healthometer.activity.entity.Activity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ActivityMapper extends DtoToEntityBidirectionalMapper<ActivityDTO, Activity> {

  ActivityDTO toDto(Activity activity);

  @Override
  @InheritInverseConfiguration
  Activity toEntity(ActivityDTO activityDTO);
}
