package com.ferhad.excellentjobs.validation;

import com.ferhad.excellentjobs.dto.payload.CandidateRegisterRequestDto;
import com.ferhad.excellentjobs.dto.payload.LoginRequestDto;
import com.ferhad.excellentjobs.dto.payload.RecruiterRegisterRequestDto;
import com.ferhad.excellentjobs.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AuthValidator {
    private static final Pattern EMAIL_REGEX_PATTERN =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public void validateLogin(LoginRequestDto loginRequest) {
        if (loginRequest.getPassword().length() > 100) {
            throw new BadRequestException("Invalid password length");
        }
    }

    public void validateCandidateRegistration(CandidateRegisterRequestDto candidateRegister) {
        if (candidateRegister.getFirstName().isBlank() || candidateRegister.getLastName().isBlank()) {
            throw new BadRequestException("Invalid inputs");
        }
        validatePassword(candidateRegister.getPassword(), candidateRegister.getConfirmPassword());
        if (!EMAIL_REGEX_PATTERN.matcher(candidateRegister.getEmail()).matches()) {
            throw new BadRequestException("Invalid email format");
        }
    }

    public void validateRecruiterRegistration(RecruiterRegisterRequestDto recruiterRegister) {
        if (recruiterRegister.getFirstName().isBlank() || recruiterRegister.getLastName().isBlank()
                || recruiterRegister.getCompanyHash().isBlank()) {
            throw new BadRequestException("Invalid inputs");
        }
        validatePassword(recruiterRegister.getPassword(), recruiterRegister.getConfirmPassword());
        if (!EMAIL_REGEX_PATTERN.matcher(recruiterRegister.getEmail()).matches()) {
            throw new BadRequestException("Invalid email format");
        }
    }

    private void validatePassword(String password, String confirmationPassword) {
        if (password.length() < 6 || password.length() > 100) {
            throw new BadRequestException("Invalid password length");
        }
        if (!password.equals(confirmationPassword)) {
            throw new BadRequestException("Invalid confirmation password");
        }
    }
}
