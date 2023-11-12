package io.everyone.travel.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "plan")
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "memo")
    private String memo;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(
            name = "travel_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "plan_travel_id_fk")
    )
    private Travel travel;

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
}
