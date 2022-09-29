package com.ferhad.excellentjobs.dto.payload;

import lombok.Data;

import java.util.List;

@Data
public class CandidateRegisterRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private List<String> labels;
}
