package com.example.asynchronousjobqueuebackgroundprocessingsystem.kafka;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import com.example.asynchronousjobqueuebackgroundprocessingsystem.redis.JobStatusRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DltConsumer {
    private final JobStatusRepository jobStatusRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "jobs-dlt" , groupId = "dlt-group")
    public void consume(String message) throws JsonProcessingException {
        Job job=mapper.readValue(message,Job.class);
        jobStatusRepository.updateStatus(job.getId(),"FAILED");
        System.out.println("Job Status updated to fail : "+job.getId());
    }
}
