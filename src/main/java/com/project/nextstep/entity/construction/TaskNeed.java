package com.project.nextstep.entity.construction;

import com.project.nextstep.entity.enums.Material;
import com.project.nextstep.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class TaskNeed {

    private String name;

    private double quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

}
