package com.microservices.job.controller;

import com.microservices.job.dto.JobWithCompanyDTO;
import com.microservices.job.model.Job;
import com.microservices.job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")

    public ResponseEntity<JobWithCompanyDTO> job(@PathVariable Long id){
        JobWithCompanyDTO job = jobService.findJobById(id);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedJob(@PathVariable Long id,@RequestBody Job job) {
        if(jobService.findJobById(id) != null) {
            jobService.updateJob(id, job);
            return ResponseEntity.ok("Updated Successfully!");
        }
        else
            return new ResponseEntity<>("No Match in Job ID!", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        if(jobService.findJobById(id) != null) {
            jobService.deleteJob(id);
            return ResponseEntity.ok("deleted Successfully!");
        }
        else
            return new ResponseEntity<>("Job id Not found", HttpStatus.NOT_FOUND);
    }

}
