package com.ferhad.excellentjobs.dto;

import lombok.Data;

@Data
public class CompanyResponseDto {
    private Long id;
    private String hash;
    private String name;
    private String location;
    private Long createdAt;
}
