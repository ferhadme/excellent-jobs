package com.ferhad.excellentjobs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "jobs", schema = "public")
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "job_sequence", sequenceName = "jobs_id_seq")
    private Long id;

    private String title;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User recruiter;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "jobs_labels",
            joinColumns = {
                    @JoinColumn(name = "jobs_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "label_id")
            }
    )
    private Set<Label> labels;

    private LocalDateTime createdAt;
}
