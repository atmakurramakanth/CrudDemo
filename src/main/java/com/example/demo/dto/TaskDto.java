package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private  int id;
    private String title;
    private String description;
    private Date due_date;
    private  String status;
}
