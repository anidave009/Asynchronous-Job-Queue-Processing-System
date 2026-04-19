package com.example.asynchronousjobqueuebackgroundprocessingsystem.kafka;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper=new ObjectMapper();

    private static final String TOPIC="jobs";
    private static final String TOPIC2="jobs-dlt";

    public void sendJob(Job job){
        try{
            String jobJson=objectMapper.writeValueAsString(job);
            kafkaTemplate.send(TOPIC,job.getId(),jobJson);
            System.out.println("Job sent to topic " + job.getId());
        }
        catch(Exception e){
            System.err.println("Failed to send job to kafka" + e.getMessage());
        }
    }

    public void sendFailedJob(Job job){
        try{
            String jobJson=objectMapper.writeValueAsString(job);
            kafkaTemplate.send(TOPIC2,job.getId(),jobJson);
            System.out.println("FailedJob sent to topic " + job.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
