package com.ferhad.excellentjobs.mapper;

import com.ferhad.excellentjobs.dto.CompanyRequestDto;
import com.ferhad.excellentjobs.dto.CompanyResponseDto;
import com.ferhad.excellentjobs.model.Company;
import com.ferhad.excellentjobs.util.TimeConverter;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company convert(CompanyRequestDto companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setLocation(companyRequest.getLocation());
        return company;
    }

    public CompanyResponseDto convert(Company company) {
        CompanyResponseDto companyResponse = new CompanyResponseDto();
        companyResponse.setId(company.getId());
        companyResponse.setHash(company.getHash());
        companyResponse.setName(company.getName());
        companyResponse.setLocation(company.getLocation());
        companyResponse.setCreatedAt(TimeConverter.convert(company.getCreatedAt()));
        return companyResponse;
    }
}
