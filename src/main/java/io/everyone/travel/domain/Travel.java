package io.everyone.travel.domain;

import io.everyone.travel.domain.enums.Nation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class Travel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Column(name = "title", nullable = false)
    String title; // 제목

    @Enumerated(EnumType.STRING)
    @Column(name = "nation")
    Nation nation; // 국가 코드

    @Column(name = "start_at")
    LocalDateTime startAt; // 계획 시작 일자

    @Column(name = "end_at")
    LocalDateTime endAt; // 계획 종료 일자

    @OneToMany(
            mappedBy = "travel",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    List<Plan> plans = new ArrayList<>();

    @OneToMany(
            mappedBy = "travel",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    List<Expense> expenses = new ArrayList<>();


    protected Travel() { }

    @Builder
    public Travel(String title, Nation nation, LocalDateTime startAt, LocalDateTime endAt) {
        this.title = title;
        this.nation = nation;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
        for (Plan plan : plans) {
            plan.setTravel(this);
        }
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        for (Expense expense : expenses) {
            expense.setTravel(this);
        }
    }

    public List<Plan> getPlans() {
        return ObjectUtils.isEmpty(plans) ? Collections.emptyList() : plans;
    }

    public List<Expense> getExpenses() {
        return ObjectUtils.isEmpty(expenses) ? Collections.emptyList() : expenses;
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
