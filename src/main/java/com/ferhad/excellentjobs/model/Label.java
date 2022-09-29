package com.ferhad.excellentjobs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "labels", schema = "public")
@Getter
@Setter
public class Label {
    @Id
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "labels")
    private Set<Job> jobs;

    @ManyToMany(mappedBy = "labels")
    private Set<User> users;

    private LocalDateTime createdAt;
}
