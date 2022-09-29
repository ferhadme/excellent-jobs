package com.ferhad.excellentjobs.resource;

import com.ferhad.excellentjobs.dto.payload.CandidateRegisterRequestDto;
import com.ferhad.excellentjobs.dto.payload.LoginRequestDto;
import com.ferhad.excellentjobs.dto.payload.LoginResponseDto;
import com.ferhad.excellentjobs.dto.payload.RecruiterRegisterRequestDto;
import com.ferhad.excellentjobs.dto.payload.RegisterResponseDto;
import com.ferhad.excellentjobs.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final AuthService authService;

    @PostMapping("/recruiter/register")
    public ResponseEntity<RegisterResponseDto> registerAsRecruiter(
            @RequestBody RecruiterRegisterRequestDto registerRequest
    ) {
        return ResponseEntity.ok(
                authService.registerAsRecruiter(registerRequest)
        );
    }

    @PostMapping("/candidate/register")
    public ResponseEntity<RegisterResponseDto> registerAsCandidate(
            @RequestBody CandidateRegisterRequestDto registerRequest
    ) {
        return ResponseEntity.ok(
                authService.registerAsCandidate(registerRequest)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(
                authService.login(loginRequest)
        );
    }
}
