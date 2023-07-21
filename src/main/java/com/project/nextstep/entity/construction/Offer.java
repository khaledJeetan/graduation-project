package com.project.nextstep.entity.construction;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Offer<T>{

    private T name;

    private String unit;

    private double price;

    private String description;

    private double totalRate;

}
