package com.example.asynchronousjobqueuebackgroundprocessingsystem.redis;

import com.example.asynchronousjobqueuebackgroundprocessingsystem.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobStatusRepository {

    private final StringRedisTemplate redisTemplate;

    public void updateStatus(String jobId,String status){
        redisTemplate.opsForValue().set(jobId,status);
    }

    public String getStatus(String jobId){
        return redisTemplate.opsForValue().get(jobId);
    }
}
