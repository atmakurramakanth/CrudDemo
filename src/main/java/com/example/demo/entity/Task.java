package com.example.demo.entity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private  int id;

    @Column(name ="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name ="due_date")
    private Date due_date;

    @Column(name ="status")
    private String status;

}
