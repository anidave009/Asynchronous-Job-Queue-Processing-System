package com.example.asynchronousjobqueuebackgroundprocessingsystem.kafka;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.redis.JobStatusRepository;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final JobStatusRepository jobStatusRepository;

    @KafkaListener(topics = "jobs" , groupId = "job-queue-group")
    public void consume(String message){
        try{
            Job job =objectMapper.readValue(message, Job.class);
            System.out.println("Received Job: " + job.getId());

            // idempotency check
            //added in consumer , coz kafka may send same msg twice
            //kafka works on atleast once delivery concept.
            String currentStatus = jobStatusRepository.getStatus(job.getId());
            if (currentStatus != null) {
                System.out.println("Job already processed, skipping: " + job.getId());
                return;
            }

            job.setStatus("PROCESSING");
            jobStatusRepository.updateStatus(job.getId(),"PROCESSING");
            System.out.println("Job Status: PROCESSING" + job.getId());

            doHeavyWork(job);

            job.setStatus("DONE");
            jobStatusRepository.updateStatus(job.getId(),"DONE");

            System.out.println("Job Status: DONE"+job.getId());
        } catch (Exception e) {
            System.err.println("Failed to process job" + e.getMessage());
        }

//commented this out to test failures , system reties 3 times the message and then pushes to
//dlt-topic , dltconsumer listens to it and updates the jobstatus in redis , which user can
//see . It shows failed for a job , helping user track the job status.

//        Job job=objectMapper.convertValue(message, Job.class);
//        System.out.println("Received Job: "+job.getId());
//        jobStatusRepository.updateStatus(job.getId(),"PROCESSING");
//        throw new RuntimeException("Simulated failure for job "+job.getId());
    }


    private void doHeavyWork(Job job) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
