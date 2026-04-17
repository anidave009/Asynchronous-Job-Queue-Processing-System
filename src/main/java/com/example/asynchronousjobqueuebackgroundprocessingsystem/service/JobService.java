package com.example.asynchronousjobqueuebackgroundprocessingsystem.service;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class JobService {

    public Job submitJob(String jobType){
        Job job =Job.builder()
                .id(UUID.randomUUID().toString())
                .type(jobType)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
        System.out.println("Starting Job " + job.getId());
        doHeavyWork(job);
        System.out.println("Finished Job " + job.getId());
        return job;
    }

    private void doHeavyWork(Job job){
        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
