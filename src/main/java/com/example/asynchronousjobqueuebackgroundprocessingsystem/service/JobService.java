package com.example.asynchronousjobqueuebackgroundprocessingsystem.service;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.kafka.JobProducer;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.redis.JobStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobProducer jobProducer;
    private final JobStatusRepository jobStatusRepository;

    public Job submitJob(String jobType) {
        Job job = Job.builder()
                .id(UUID.randomUUID().toString())
                .type(jobType)
                .status("PENDING")
                .createdAt(LocalDateTime.now().toString())
                .build();
        // write PENDING to Redis
        jobStatusRepository.updateStatus(job.getId(), "PENDING");
        jobProducer.sendJob(job);
        System.out.println("Job submitted to topic " + job.getId());
        return job;
    }
}