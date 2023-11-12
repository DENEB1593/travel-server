package io.everyone.travel.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "expense")
public class Expense extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amt", nullable = false)
    private BigDecimal amt;

    @ManyToOne
    @JoinColumn(
            name = "travel_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "expense_travel_id_fk")
    )
    private Travel travel;

    protected Expense() { }

    @Builder
    public Expense(BigDecimal amt) {
        this.amt = amt;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}
