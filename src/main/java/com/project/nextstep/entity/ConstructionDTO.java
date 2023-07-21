package com.project.nextstep.entity;


import com.project.nextstep.entity.enums.HouseFinishingTask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConstructionDTO {

    private House house;
    private Map<HouseFinishingTask,Boolean> plan;

}
