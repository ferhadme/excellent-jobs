package com.ferhad.excellentjobs.service;

import com.ferhad.excellentjobs.dto.UserDto;
import com.ferhad.excellentjobs.dto.payload.CandidateRegisterRequestDto;
import com.ferhad.excellentjobs.dto.payload.LoginRequestDto;
import com.ferhad.excellentjobs.dto.payload.LoginResponseDto;
import com.ferhad.excellentjobs.dto.payload.RecruiterRegisterRequestDto;
import com.ferhad.excellentjobs.exceptions.DuplicateEmailException;
import com.ferhad.excellentjobs.exceptions.NotFoundException;
import com.ferhad.excellentjobs.mapper.AuthMapper;
import com.ferhad.excellentjobs.mapper.CompanyMapper;
import com.ferhad.excellentjobs.mapper.UserMapper;
import com.ferhad.excellentjobs.model.Company;
import com.ferhad.excellentjobs.model.Role;
import com.ferhad.excellentjobs.model.User;
import com.ferhad.excellentjobs.model.enums.ERole;
import com.ferhad.excellentjobs.repository.CompanyRepository;
import com.ferhad.excellentjobs.repository.LabelRepository;
import com.ferhad.excellentjobs.repository.RoleRepository;
import com.ferhad.excellentjobs.repository.UserRepository;
import com.ferhad.excellentjobs.security.jwt.JwtUtils;
import com.ferhad.excellentjobs.validation.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;
    private final CompanyMapper companyMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LabelService labelService;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthValidator authValidator;

    public LoginResponseDto login(LoginRequestDto loginRequest) {
        authValidator.validateLogin(loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return LoginResponseDto.builder()
                .token(jwt)
                .build();
    }

    public UserDto registerAsCandidate(CandidateRegisterRequestDto candidateRegister) {
        authValidator.validateCandidateRegistration(candidateRegister);
        if (userRepository.existsByEmail(candidateRegister.getEmail())) {
            throw new DuplicateEmailException("Email " + candidateRegister.getEmail() + " is already token");
        }

        User user = authMapper.convert(candidateRegister);
        Role role = roleRepository.findByName(ERole.ROLE_CANDIDATE)
                .orElseThrow(() -> new NotFoundException("CANDIDATE Role not found"));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(candidateRegister.getPassword()));
        user.setLabels(labelService.initializeLabels(candidateRegister.getLabels()));
        user.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        return userMapper.convert(
                userRepository.save(user), null
        );
    }

    public UserDto registerAsRecruiter(RecruiterRegisterRequestDto recruiterRegister) {
        authValidator.validateRecruiterRegistration(recruiterRegister);
        if (userRepository.existsByEmail(recruiterRegister.getEmail())) {
            throw new DuplicateEmailException("Email " + recruiterRegister.getEmail() + " is already token");
        }

        User user = authMapper.convert(recruiterRegister);
        Role role = roleRepository.findByName(ERole.ROLE_RECRUITER)
                .orElseThrow(() -> new NotFoundException("RECRUITER Role not found"));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(recruiterRegister.getPassword()));
        Company company = companyRepository.findByHash(recruiterRegister.getCompanyHash())
                        .orElseThrow(() -> new NotFoundException("Company not found"));
        user.setCompany(company);
        user.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        return userMapper.convert(
                userRepository.save(user), companyMapper.convert(company)
        );
    }

}
