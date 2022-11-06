package edu.miu.lab4.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LogDto {
    long id;
    private long userId;
    private String operation;
    private String endPoint;
    private String method;
    private String params;
    private LocalDateTime requestTime;
}
