package com.ferhad.excellentjobs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies", schema = "public")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_sequence", sequenceName = "companies_id_seq")
    private Long id;

    private String hash;
    private String name;
    private String location;
    private LocalDateTime createdAt;
}