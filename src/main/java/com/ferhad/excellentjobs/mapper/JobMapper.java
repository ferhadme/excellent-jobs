package com.ferhad.excellentjobs.mapper;

import com.ferhad.excellentjobs.dto.JobRequestDto;
import com.ferhad.excellentjobs.dto.JobResponseDto;
import com.ferhad.excellentjobs.dto.UserDto;
import com.ferhad.excellentjobs.model.Job;
import com.ferhad.excellentjobs.util.TimeConverter;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public Job convert(JobRequestDto jobRequest) {
        Job job = new Job();
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        return job;
    }

    public JobResponseDto convert(Job job, UserDto userDto) {
        JobResponseDto jobResponse = new JobResponseDto();
        jobResponse.setId(job.getId());
        jobResponse.setTitle(job.getTitle());
        jobResponse.setDescription(job.getDescription());
        jobResponse.setPostedBy(userDto);
        jobResponse.setCreatedAt(TimeConverter.convert(job.getCreatedAt()));
        return jobResponse;
    }
}
