package com.shashi.JobApplication.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void create(Job job);

    Job findById(Long id);

    boolean deleteById(Long id);

    boolean update(Long id, Job job);
}
