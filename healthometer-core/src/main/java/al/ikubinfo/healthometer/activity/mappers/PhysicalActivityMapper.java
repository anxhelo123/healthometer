package al.ikubinfo.healthometer.activity.mappers;

import al.ikubinfo.commons.mapper.DtoToEntityBidirectionalMapper;
import al.ikubinfo.healthometer.activity.dto.PhysicalActivityDTO;
import al.ikubinfo.healthometer.activity.entity.PhysicalActivity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PhysicalActivityMapper
    extends DtoToEntityBidirectionalMapper<PhysicalActivityDTO, PhysicalActivity> {

  PhysicalActivityDTO toDto(PhysicalActivity physicalActivity);

  @Override
  @InheritInverseConfiguration
  PhysicalActivity toEntity(PhysicalActivityDTO physicalActivityDTO);
}
