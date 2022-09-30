package com.ferhad.excellentjobs.dto;

import lombok.Data;

@Data
public class JobResponseDto {
    private Long id;
    private String title;
    private String description;
    private UserDto postedBy;
    private Long createdAt;
}
