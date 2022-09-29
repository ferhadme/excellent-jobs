package com.ferhad.excellentjobs.dto.payload;

import com.ferhad.excellentjobs.dto.CompanyResponseDto;
import lombok.Data;

@Data
public class RegisterResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private CompanyResponseDto company;
    private Long createdAt;
}
