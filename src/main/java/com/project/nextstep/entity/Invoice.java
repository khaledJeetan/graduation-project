package com.project.nextstep.entity;

import com.project.nextstep.entity.accounts.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Invoice extends EntityDateMetadata {
    @Id
    private Long id;

    private int quantity;

    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
