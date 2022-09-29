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
@Table(name = "jobs", schema = "public")
@Getter
@Setter
public class Job {
    @Id
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    private User recruiter;

    @ManyToMany
    @JoinTable(
            name = "jobs_labels",
            joinColumns = {
                    @JoinColumn(name = "job_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "label_id")
            }
    )
    private Set<Label> labels;

    private LocalDateTime createdAt;
}
