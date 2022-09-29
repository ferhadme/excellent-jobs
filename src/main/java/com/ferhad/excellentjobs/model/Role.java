package com.ferhad.excellentjobs.model;

import com.ferhad.excellentjobs.model.enums.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles", schema = "public")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_sequence", sequenceName = "roles_id_seq")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ERole name;
}
