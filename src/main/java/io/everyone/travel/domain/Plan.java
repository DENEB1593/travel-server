package io.everyone.travel.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "plan")
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(
            name = "travel_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "plan_travel_id_fk")
    )
    private Travel travel;

    protected Plan() { }

    @Builder
    public Plan(String content) {
        this.content = content;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}
