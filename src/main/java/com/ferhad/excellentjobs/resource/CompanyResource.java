package com.ferhad.excellentjobs.resource;

import com.ferhad.excellentjobs.dto.CompanyRequestDto;
import com.ferhad.excellentjobs.dto.CompanyResponseDto;
import com.ferhad.excellentjobs.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyResource {
    private final CompanyService companyService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CompanyResponseDto> createCompany(
            @RequestBody CompanyRequestDto companyRequest
    ) {
        return ResponseEntity.ok(
                companyService.create(companyRequest)
        );
    }
}