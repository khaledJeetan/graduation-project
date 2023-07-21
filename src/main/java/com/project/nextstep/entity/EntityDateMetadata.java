package com.project.nextstep.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class EntityDateMetadata {

    @Column(name = "date_created")
    protected LocalDateTime dateCreated;

    @Column(name = "last_modified")
    protected LocalDateTime lastModified;

    @PrePersist
    public void prePersist(){
        this.dateCreated = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.lastModified = LocalDateTime.now();
    }

}
