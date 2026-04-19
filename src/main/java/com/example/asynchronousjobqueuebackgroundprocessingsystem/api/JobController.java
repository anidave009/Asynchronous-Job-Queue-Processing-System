package com.example.asynchronousjobqueuebackgroundprocessingsystem.api;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.redis.JobStatusRepository;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final JobStatusRepository jobStatusRepository;

    @PostMapping("/submit-job")
    public ResponseEntity<Job> submitJob(@RequestParam String jobType) {

        Job job=jobService.submitJob(jobType);
        return ResponseEntity.ok(job);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<String> getJobStatus(@PathVariable String id) {
        String status= jobStatusRepository.getStatus(id);
        System.out.println("Job Status: "+status);

        if(status==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(status);
    }

}
