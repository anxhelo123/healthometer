package al.ikubinfo.healthometer.activity.repository;

import al.ikubinfo.healthometer.activity.entity.Activity;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

  Activity findByTodayDate(Date date);
}
