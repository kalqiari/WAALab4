package edu.miu.lab4.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "logs")

public class Log {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id;
    private long userId;
    private String operation;
    private String endPoint;
    private String method;
    private String params;
    private LocalDateTime requestTime;
}
