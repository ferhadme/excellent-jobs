package com.ferhad.excellentjobs.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private CompanyResponseDto company;
    private Long createdAt;
}
