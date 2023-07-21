package com.project.nextstep.entity.accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.Chat;
import com.project.nextstep.entity.EntityDateMetadata;
import com.project.nextstep.entity.Notification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

import java.util.List;

@Getter
@Setter
@Entity(name = "user_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@DiscriminatorFormula("CASE " +
        "when role = 'SUPPLIER' then 'SUPPLIER'" +
        " when role = 'ADMIN' then 'ADMIN'" +
        " when role = 'CLIENT' then 'CLIENT'" +
        "when role = 'VENDOR' then 'VENDOR'" +
        "ELSE 'CLIENT' END")
@JsonIgnoreProperties("notifications")
public class User extends EntityDateMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(
            name = "first_name",
            length = 45,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            length = 45,
            nullable = false
    )
    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 10)
    private String phone;

    private String location;

    @Column(name = "role")
    private String role;

    private String url;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @JsonIgnoreProperties("user")
    List<Notification> notifications;

}
