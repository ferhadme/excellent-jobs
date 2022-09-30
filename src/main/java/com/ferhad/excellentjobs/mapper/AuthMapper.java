package com.ferhad.excellentjobs.mapper;

import com.ferhad.excellentjobs.dto.payload.CandidateRegisterRequestDto;
import com.ferhad.excellentjobs.dto.payload.RecruiterRegisterRequestDto;
import com.ferhad.excellentjobs.model.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
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
