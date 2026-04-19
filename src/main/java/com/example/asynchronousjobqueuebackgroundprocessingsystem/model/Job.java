package com.example.asynchronousjobqueuebackgroundprocessingsystem.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
    private String id;

    private String type;

    private String status;

    private String createdAt;
}
