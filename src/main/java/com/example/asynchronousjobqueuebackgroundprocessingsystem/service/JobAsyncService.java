package com.example.asynchronousjobqueuebackgroundprocessingsystem.service;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobAsyncService {

    @Async
    public void processJobAsync(Job job) {
        System.out.println("Processing Job " + job.getId());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Finished Job " + job.getId());
    }
}
