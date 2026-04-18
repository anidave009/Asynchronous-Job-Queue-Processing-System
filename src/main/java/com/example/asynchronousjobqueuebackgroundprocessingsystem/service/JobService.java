package com.example.asynchronousjobqueuebackgroundprocessingsystem.service;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class JobService {

    private final JobAsyncService jobAsyncService;

    public JobService(JobAsyncService jobAsyncService) {
        this.jobAsyncService = jobAsyncService;
    }

    public Job submitJob(String jobType){
        Job job = Job.builder()
                .id(UUID.randomUUID().toString())
                .type(jobType)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        System.out.println("Submitting Job " + job.getId());

        jobAsyncService.processJobAsync(job); // async call

        return job;
    }
}
