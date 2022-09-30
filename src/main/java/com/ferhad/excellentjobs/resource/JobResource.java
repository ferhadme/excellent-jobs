package com.ferhad.excellentjobs.resource;

import com.ferhad.excellentjobs.dto.JobRequestDto;
import com.ferhad.excellentjobs.dto.JobResponseDto;
import com.ferhad.excellentjobs.dto.LabelDto;
import com.ferhad.excellentjobs.dto.UserDto;
import com.ferhad.excellentjobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobResource {
    private final JobService jobService;

    @PostMapping
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<JobResponseDto> postJob(
            Principal principal,
            @RequestBody JobRequestDto jobRequest
    ) {
        return ResponseEntity.ok(
                jobService.postJob(principal, jobRequest)
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<List<JobResponseDto>> searchJobs(
            Principal principal
    ) {
        return ResponseEntity.ok(
                jobService.searchJobs(principal)
        );
    }

    @PostMapping("/candidates")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<List<UserDto>> searchCandidates(
            LabelDto labelDto
    ) {
        return ResponseEntity.ok(
                jobService.searchCandidates(labelDto)
        );
    }
}
