package com.ferhad.excellentjobs.model;

import com.ferhad.excellentjobs.model.enums.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles", schema = "public")
@Getter
@Setter
public class Role {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;
}
