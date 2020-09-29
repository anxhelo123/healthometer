package al.ikubinfo.healthometer.activity.entity;

import al.ikubinfo.commons.entity.BaseEntity;
import al.ikubinfo.healthometer.users.entity.UserEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Table(name = "activity")
public class Activity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  protected Long id;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
  @Column(name = "food")
  private List<Food> food;

  @Column(name = "waterQuantityInMl")
  private int waterQuantityInMl;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
  @Column(name = "physicalActivity") // see if it need to join with physical activity table
  private List<PhysicalActivity> physicalActivity;

  @Column(name = "todayDate")
  @CreatedDate
  private Date todayDate;

  @CreatedBy UserEntity createdBy;
}
