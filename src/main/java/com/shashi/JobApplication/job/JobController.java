package com.shashi.JobApplication.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {
    private List<Job> jobs=new ArrayList<>();
    private JobService jobService;
    public JobController(JobService jobService){
        this.jobService=jobService;
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.create(job);
         return new ResponseEntity<>("jobs Added succesfully", HttpStatus.CREATED);

    }
    @GetMapping("/getJobById/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobService.findById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted=jobService.deleteById(id);
        if(deleted){
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job NOT FOUND", HttpStatus.NOT_FOUND);
        }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> createJob(@PathVariable Long id,@RequestBody Job job){
        boolean jobs=jobService.update(id,job);
       if(jobs){
           return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
       }
           return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);

    }
}
