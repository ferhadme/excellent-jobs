package com.ferhad.excellentjobs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies", schema = "public")
@Getter
@Setter
public class Company {
    @Id
    private Long id;

    private String name;
    private String location;
    private LocalDateTime createdAt;
}