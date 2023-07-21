package com.project.nextstep.entity.accounts;//package com.project.nextstep.entity;

import jakarta.persistence.*;

@Entity(name = "admin")
@DiscriminatorValue("ADMIN")
public class Admin extends User {

}
