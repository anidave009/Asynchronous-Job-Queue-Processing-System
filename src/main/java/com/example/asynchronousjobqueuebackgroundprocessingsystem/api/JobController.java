package com.example.asynchronousjobqueuebackgroundprocessingsystem.api;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping("/submit-job")
    public ResponseEntity<Job> submitJob(@RequestParam String jobType) {

        Job job=jobService.submitJob(jobType);
        return ResponseEntity.ok(job);
    }
}
