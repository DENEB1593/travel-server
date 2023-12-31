package io.everyone.travel.core.domain.plan.entity;

import io.everyone.travel.core.config.BaseEntity;
import io.everyone.travel.core.domain.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "plan")
@SQLDelete(sql = "update plan set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "memo")
    String memo;

    @Column(name = "start_at", nullable = false)
    LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(
        name = "travel_id",
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "plan_travel_id_fk")
    )
    Travel travel;

    protected Plan() { }


    @Builder
    public Plan(String title, String memo, LocalDateTime startAt, LocalDateTime endAt) {
        this.title = title;
        this.memo = memo;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public void updateFromRequest(String title, String memo) {
        this.title = title;
        this.memo = memo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("title", title)
            .append("memo", memo)
            .append("startAt", startAt)
            .append("endAt", endAt)
            .toString();
    }


}
