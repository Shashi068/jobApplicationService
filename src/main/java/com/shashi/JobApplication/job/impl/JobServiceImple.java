package com.shashi.JobApplication.job.impl;

import com.shashi.JobApplication.job.Job;
import com.shashi.JobApplication.job.JobRepo;
import com.shashi.JobApplication.job.JobService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class JobServiceImple implements JobService {
     JobRepo jobRepo;

    public JobServiceImple(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public List<Job> findAll() {
        return jobRepo.findAll();
    }

    @Override
    public void create(Job job) {
        jobRepo.save(job);
    }

    @Override
    public Job findById(Long id) {
        return jobRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if(jobRepo.existsById(id)){
            jobRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Job job) {
        Job job1=jobRepo.findById(id).orElse(null);{
            if(job1.getId().equals(id)){
                job1.setTitle(job.getTitle());
                job1.setDescription(job.getDescription());
                job1.setMinSalary((job.getMinSalary()));
                job1.setMaxSalary(job.getMaxSalary());
                job1.setLocation(job.getLocation());
                jobRepo.save(job1);
                return true;
            }
        }
        return false;
    }
}
