package com.project.nextstep.entity.accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.Construction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity(name = "client")
@DiscriminatorValue("CLIENT")
public class Client extends User {

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties("client")
    private List<Construction> constructionList;

}
