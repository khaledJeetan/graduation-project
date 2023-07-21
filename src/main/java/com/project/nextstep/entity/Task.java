package com.project.nextstep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.accounts.Vendor;
import com.project.nextstep.entity.construction.Order;
import com.project.nextstep.entity.construction.TaskNeed;
import com.project.nextstep.entity.enums.HouseFinishingTask;
import com.project.nextstep.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Getter
@Setter
@Entity
public class Task extends EntityDateMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    @SequenceGenerator(name = "task_generator", sequenceName = "task_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private double progress;

    private LocalDate startDate;

    private LocalDate EndDate;

    @Enumerated(EnumType.STRING)
    private HouseFinishingTask taskName;

    @Transient
    private Period period;

    @Column(length = 45,nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "vendor_id")
    @JsonIgnoreProperties({"tasks"})
    private Vendor vendor;

//    @ManyToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "step_task_list_id")
//    @JsonIgnoreProperties("tasks")
//    private StepTaskList stepTaskList;
//
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "construction_id")
    @JsonIgnoreProperties({"plan"})
    private  Construction construction;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task" )
    @JsonIgnoreProperties({"task"})
    private List<Order> order;

    @ElementCollection
    @CollectionTable(name = "task_need")
    private List<TaskNeed> taskNeeds;



}
