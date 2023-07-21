package com.project.nextstep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.accounts.Client;
import com.project.nextstep.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Construction extends EntityDateMetadata{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "construction_generator")
    @SequenceGenerator(name = "construction_generator", sequenceName = "construction_sequence", allocationSize = 1)
    private Long id;

    private double progress;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isCustom;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            optional = false
    )
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("constructionList")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private House house;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "step_name")
    @MapKeyJoinColumn(name = "step_task_list_id")
    private Map<String, StepTaskList> plan;

}
