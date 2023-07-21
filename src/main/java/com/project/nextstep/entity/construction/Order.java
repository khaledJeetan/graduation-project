package com.project.nextstep.entity.construction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.EntityDateMetadata;
import com.project.nextstep.entity.Task;
import com.project.nextstep.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends EntityDateMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "order_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Product product;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "task_id")
    @JsonIgnoreProperties({"order"})
    private Task task;

    private double quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
