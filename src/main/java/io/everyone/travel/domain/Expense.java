package io.everyone.travel.domain;

import io.everyone.travel.controller.dto.ExpenseUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "expense")
@SQLDelete(sql = "UPDATE expense SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Expense extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Column(name = "amt", nullable = false)
    BigDecimal amt;

    @ManyToOne
    @JoinColumn(
        name = "travel_id",
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "expense_travel_id_fk")
    )
    Travel travel;

    protected Expense() { }

    @Builder
    public Expense(BigDecimal amt) {
        this.amt = amt;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }


    public void updateFromRequest(ExpenseUpdateRequest request) {
        this.amt = request.amt();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("amt", amt)
            .toString();
    }

}
