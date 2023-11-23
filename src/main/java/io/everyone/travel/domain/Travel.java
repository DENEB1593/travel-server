package io.everyone.travel.domain;

import io.everyone.travel.controller.dto.TravelUpdateRequest;
import io.everyone.travel.domain.enums.Nation;
import io.everyone.travel.util.EnumSupports;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "travel")
@SQLDelete(sql = "UPDATE travel SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Travel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Column(name = "title", nullable = false)
    String title; // 제목

    @Enumerated(EnumType.STRING)
    @Column(name = "nation", nullable = false)
    Nation nation; // 국가 코드

    @Column(name = "start_at", nullable = false)
    LocalDateTime startAt; // 계획 시작 일자

    @Column(name = "end_at", nullable = false)
    LocalDateTime endAt; // 계획 종료 일자

    @Column(name = "thumbnail")
    String thumbnail; // 썸네일(이미지)

    @OneToMany(
        mappedBy = "travel",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    Set<Plan> plans = new HashSet<>();

    @OneToMany(
        mappedBy = "travel",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    Set<Expense> expenses = new HashSet<>();


    protected Travel() { }

    @Builder
    public Travel(String title, Nation nation, LocalDateTime startAt, LocalDateTime endAt, String thumbnail) {
        this.title = title;
        this.nation = nation;
        this.startAt = startAt;
        this.endAt = endAt;
        this.thumbnail = thumbnail;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
        for (Plan plan : plans) {
            plan.setTravel(this);
        }
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
        for (Expense expense : expenses) {
            expense.setTravel(this);
        }
    }

    public Set<Plan> getPlans() {
        return ObjectUtils.isEmpty(plans) ? Collections.emptySet() : plans;
    }

    public Set<Expense> getExpenses() {
        return ObjectUtils.isEmpty(expenses) ? Collections.emptySet() : expenses;
    }

    public void updateThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;  // 썸네일 경로를 저장한다.
    }

    public void updateFromRequest(TravelUpdateRequest request) {
        this.title = request.title();
        this.nation = EnumSupports.byEnumName(Nation.class, request.nation());
        this.startAt = request.startAt();
        this.endAt = request.endAt();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("title", title)
            .append("nation", nation)
            .append("startAt", startAt)
            .append("endAt", endAt)
            .toString();
    }


}
