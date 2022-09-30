package com.ferhad.excellentjobs.service;

import com.ferhad.excellentjobs.dto.JobRequestDto;
import com.ferhad.excellentjobs.dto.JobResponseDto;
import com.ferhad.excellentjobs.dto.LabelDto;
import com.ferhad.excellentjobs.dto.UserDto;
import com.ferhad.excellentjobs.exceptions.NotFoundException;
import com.ferhad.excellentjobs.mapper.CompanyMapper;
import com.ferhad.excellentjobs.mapper.JobMapper;
import com.ferhad.excellentjobs.mapper.UserMapper;
import com.ferhad.excellentjobs.model.Job;
import com.ferhad.excellentjobs.model.Label;
import com.ferhad.excellentjobs.model.User;
import com.ferhad.excellentjobs.repository.JobRepository;
import com.ferhad.excellentjobs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final LabelService labelService;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;
    private final UserMapper userMapper;
    private final CompanyMapper companyMapper;

    public JobResponseDto postJob(Principal principal, JobRequestDto jobRequest) {
        Job job = jobMapper.convert(jobRequest);
        User recruiter = userRepository.findByEmail(principal.getName())
                        .orElseThrow(() -> new NotFoundException("Recruiter not found"));
        job.setRecruiter(recruiter);
        job.setLabels(labelService.initializeLabels(jobRequest.getLabels()));
        job.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        return jobMapper.convert(
                jobRepository.save(job),
                userMapper.convert(recruiter,
                        companyMapper.convert(recruiter.getCompany())
                )
        );
    }

    public List<JobResponseDto> searchJobs(Principal principal) {
        User candidate = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("Candidate not found"));
        Set<Label> labels = candidate.getLabels();
        return jobRepository.findAllByLabels(labels).stream()
                .map(e -> jobMapper.convert(e,
                        userMapper.convert(e.getRecruiter(),
                                companyMapper.convert(e.getRecruiter().getCompany()))))
                .collect(Collectors.toList());
    }

    public List<UserDto> searchCandidates(LabelDto labelDto) {
        Set<Label> labels = labelService.getLabels(labelDto.getLabels());
        return userRepository.findAllByLabels(labels).stream()
                .map(e -> userMapper.convert(e, null))
                .collect(Collectors.toList());
    }
}
