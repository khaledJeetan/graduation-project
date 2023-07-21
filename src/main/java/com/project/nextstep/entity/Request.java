package com.project.nextstep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.accounts.Vendor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_generator")
    @SequenceGenerator(name = "request_generator", sequenceName = "request_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonIgnoreProperties({"stepTaskList","order"})
    private Task task;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

}
