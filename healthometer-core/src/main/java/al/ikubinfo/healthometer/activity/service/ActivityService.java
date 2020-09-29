package al.ikubinfo.healthometer.activity.service;

import al.ikubinfo.healthometer.activity.dto.ActivityDTO;
import al.ikubinfo.healthometer.activity.entity.Activity;
import al.ikubinfo.healthometer.activity.mappers.ActivityMapper;
import al.ikubinfo.healthometer.activity.repository.ActivityRepository;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ActivityService {

  private ActivityRepository activityRepository;
  private ActivityMapper activityMapper;

  public ActivityDTO insertActivity(ActivityDTO activityDto, Date date) {
    activityDto.setTodayDate(date);
    return activityMapper.toDto(activityRepository.save(activityMapper.toEntity(activityDto)));
  }

  public ActivityDTO getActivityByDate(Date date) {
    Activity activity = activityRepository.findByTodayDate(date);
    return activityMapper.toDto(activity);
  }

  public ActivityDTO updateActivity(ActivityDTO activityDTO, Date date) {
    Activity activity = activityRepository.findByTodayDate(date);
    activity.setFood(activityDTO.getFood());
    activity.setPhysicalActivity(activityDTO.getPhysicalActivity());
    return activityMapper.toDto(activityRepository.save(activity));
  }

  public void deleteActivity(Date date) {
    Activity activity = activityRepository.findByTodayDate(date);
    activityRepository.save(activity);
  }
}
