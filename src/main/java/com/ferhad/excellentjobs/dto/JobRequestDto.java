package com.ferhad.excellentjobs.dto;

import lombok.Data;

import java.util.List;

@Data
public class JobRequestDto {
    private String title;
    private String description;
    private List<String> labels;
}