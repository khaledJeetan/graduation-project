package com.project.nextstep.entity.accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.construction.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "supplier")
@DiscriminatorValue("SUPPLIER")
public class Supplier extends User {

    @OneToMany(mappedBy = "supplier", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnoreProperties("supplier")
    private List<Product> products;

}
