package com.ferhad.excellentjobs.service;

import com.ferhad.excellentjobs.dto.CompanyRequestDto;
import com.ferhad.excellentjobs.dto.CompanyResponseDto;
import com.ferhad.excellentjobs.mapper.CompanyMapper;
import com.ferhad.excellentjobs.model.Company;
import com.ferhad.excellentjobs.repository.CompanyRepository;
import com.ferhad.excellentjobs.util.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyResponseDto create(CompanyRequestDto companyRequest) {
        Company company = companyMapper.convert(companyRequest);
        company.setHash(Hashing.sha256Hash(company.getName()));
        company.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        return companyMapper.convert(
                companyRepository.save(company)
        );
    }
}
