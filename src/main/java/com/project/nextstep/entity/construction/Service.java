package com.project.nextstep.entity.construction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.accounts.Vendor;
import com.project.nextstep.entity.enums.HouseFinishingTask;
import com.project.nextstep.entity.enums.ServiceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Service extends Offer<HouseFinishingTask> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_generator")
    @SequenceGenerator(name = "service_generator", sequenceName = "service_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(
            optional = false
    )
    @JsonIgnoreProperties("service")
    private Vendor vendor;

}
