package com.project.nextstep.entity;

import com.project.nextstep.entity.enums.BudgetCategory;
import com.project.nextstep.entity.enums.BudgetStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_generator")
    @SequenceGenerator(name = "budget_generator", sequenceName = "budget_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "Budget_expenses")
    private List<BudgetCategory> categories;

    @Enumerated(EnumType.STRING)
    private BudgetStatus status;

    private double estimatedAmount;

    private double actualAmount;

    private String note;

    @Transient
    private double variance;

    public double getVariance() {
        return this.estimatedAmount - this.actualAmount;
    }
}

