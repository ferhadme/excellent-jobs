package com.ferhad.excellentjobs.mapper;

import com.ferhad.excellentjobs.dto.CompanyResponseDto;
import com.ferhad.excellentjobs.dto.payload.CandidateRegisterRequestDto;
import com.ferhad.excellentjobs.dto.payload.RecruiterRegisterRequestDto;
import com.ferhad.excellentjobs.dto.payload.RegisterResponseDto;
import com.ferhad.excellentjobs.model.User;
import com.ferhad.excellentjobs.util.TimeConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public RegisterResponseDto convert(User user, CompanyResponseDto company) {
        RegisterResponseDto registerResponse = new RegisterResponseDto();
        registerResponse.setId(user.getId());
        registerResponse.setFirstName(user.getFirstName());
        registerResponse.setLastName(user.getLastName());
        registerResponse.setEmail(user.getEmail());
        registerResponse.setCompany(company);
        registerResponse.setCreatedAt(TimeConverter.convert(user.getCreatedAt()));
        return registerResponse;
    }

    public User convert(CandidateRegisterRequestDto candidateRegister) {
        User user = new User();
        user.setFirstName(candidateRegister.getFirstName());
        user.setLastName(candidateRegister.getLastName());
        user.setEmail(candidateRegister.getEmail());
        return user;
    }

    public User convert(RecruiterRegisterRequestDto recruiterRegister) {
        User user = new User();
        user.setFirstName(recruiterRegister.getFirstName());
        user.setLastName(recruiterRegister.getLastName());
        user.setEmail(recruiterRegister.getEmail());
        return user;
    }
}
