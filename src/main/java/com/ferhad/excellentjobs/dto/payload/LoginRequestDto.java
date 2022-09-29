package com.ferhad.excellentjobs.dto.payload;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
