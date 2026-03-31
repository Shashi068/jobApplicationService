package com.shashi.JobApplication.job.impl;

import com.shashi.JobApplication.job.Job;
import com.shashi.JobApplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImple implements JobService {

    List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void create(Job job) {
        if(job.getId() == null){
            throw new IllegalArgumentException("Job id cannot be null");
        }
        jobs.add(job); // ✅ FIX
    }

    @Override
    public Job findById(Long id) {
        for(Job job : jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return jobs.removeIf(job -> job.getId().equals(id));
    }

    @Override
    public boolean update(Long id, Job job) {
        for(Job job1:jobs){
            if(job1.getId().equals(id)){
                job1.setTitle(job.getTitle());
                job1.setDescription(job.getDescription());
                job1.setMinSalary((job.getMinSalary()));
                job1.setMaxSalary(job.getMaxSalary());
                job1.setLocation(job.getLocation());
                return true;
            }
        }
        return false;
    }
}
