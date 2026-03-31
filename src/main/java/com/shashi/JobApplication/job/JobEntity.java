package com.shashi.JobApplication.job;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "job_table")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private String minSalary;
    @NonNull
    private String maxSalary;
    @NonNull
    private String location;

    public JobEntity(Long id, String title, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }
}
