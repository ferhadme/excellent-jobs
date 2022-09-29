package com.ferhad.excellentjobs.dto.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
}