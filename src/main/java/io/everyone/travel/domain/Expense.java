package io.everyone.travel.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name = "expense")
public class Expense extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amt")
    private BigDecimal amt;

    @ManyToOne
    @JoinColumn(
            name = "travel_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "expense_travel_id_fk")
    )
    private Travel travel;

}
