package com.ferhad.excellentjobs.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "labels", schema = "public")
@Getter
@Setter
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "label_sequence", sequenceName = "labels_id_seq")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "labels", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<Job> jobs;

    @ManyToMany(mappedBy = "labels", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<User> users;

    private LocalDateTime createdAt;
}
