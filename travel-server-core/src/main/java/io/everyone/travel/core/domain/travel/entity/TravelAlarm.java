package io.everyone.travel.core.domain.travel.entity;

import io.everyone.travel.core.config.BaseEntity;
import io.everyone.travel.core.domain.travel.enums.Nation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "travel_alarm")
@ToString
public class TravelAlarm extends BaseEntity {

//	@Id
//	Long id;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Enumerated(EnumType.STRING)
	@Column(name = "nation", updatable = false, nullable = false)
	Nation nation;

	@Column(name = "alarm_lvl")
	String alarmLvl;

    @Column(name = "region_ty")
    String regionTy;

    @Column(name = "remark")
    String remark;

    @Column(name = "written_dt")
    LocalDate writtenDt;

    protected TravelAlarm() { }
    @Builder
    public TravelAlarm(
		Nation nation,
        String alarmLvl,
        String regionTy,
        String remark,
        LocalDate writtenDt) {
		this.nation = nation;
        this.alarmLvl = alarmLvl;
        this.regionTy = regionTy;
        this.remark = remark;
        this.writtenDt = writtenDt;
    }

}
