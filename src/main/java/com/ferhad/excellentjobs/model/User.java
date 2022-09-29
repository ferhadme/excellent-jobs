package com.ferhad.excellentjobs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
public class User {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "users_labels",
            joinColumns = {
                    @JoinColumn(name = "user_id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "label_id")
            }
    )
    private Set<Label> labels;

    private LocalDateTime createdAt;
}
