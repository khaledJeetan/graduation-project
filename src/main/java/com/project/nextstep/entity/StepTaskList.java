package com.project.nextstep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class StepTaskList extends EntityDateMetadata{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "step_task_list_generator")
    @SequenceGenerator(name = "step_task_list_generator", sequenceName = "step_task_list_sequence", allocationSize = 1)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "step_tasks")
    @JsonIgnoreProperties("stepTaskList")
    private List<Task> tasks;

}
