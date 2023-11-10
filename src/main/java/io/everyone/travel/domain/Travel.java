package io.everyone.travel.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table
public class Travel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; // 제목

    @Column(name = "start_at")
    private LocalDateTime startAt; // 계획 시작 일자

    @Column(name = "end_at")
    private LocalDateTime endAt; // 계획 종료 일자

    @OneToMany(
            mappedBy = "travel",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Plan> plans = new ArrayList<>();

    @OneToMany(
            mappedBy = "travel",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Expense> expenses = new ArrayList<>();

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
}
