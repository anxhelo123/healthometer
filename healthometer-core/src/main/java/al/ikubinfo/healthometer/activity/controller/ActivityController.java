package al.ikubinfo.healthometer.activity.controller;

import al.ikubinfo.healthometer.activity.dto.ActivityDTO;
import al.ikubinfo.healthometer.activity.service.ActivityService;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "activity")
@AllArgsConstructor
public class ActivityController {

  private ActivityService activityService;

  @GetMapping(path = "{date}")
  public ResponseEntity<ActivityDTO> getActivityByDate(@PathVariable Date date) {

    return new ResponseEntity<>(activityService.getActivityByDate(date), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ActivityDTO> insertActivity(
      @RequestBody ActivityDTO activityDTO, Date date) {

    return new ResponseEntity<>(activityService.insertActivity(activityDTO, date), HttpStatus.OK);
  }
}
