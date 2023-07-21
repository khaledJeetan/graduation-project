package com.project.nextstep.entity.construction;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Image {

    private String name;

    private String path;

}
