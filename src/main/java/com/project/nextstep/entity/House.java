package com.project.nextstep.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class House extends EntityDateMetadata{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_generator")
    @SequenceGenerator(name = "house_generator", sequenceName = "house_sequence", allocationSize = 1)
    private Long id;

    private String projectName;

    private String location;

    private int roomsNumber;

    @Column(name = "house_size")
    private double size;

    @Column(name = "min_budget")
    private double minBudget;

    @Column(name = "max_budget")
    private double maxBudget;

    private LocalDate startDate = LocalDate.now();

    private String url;

}
