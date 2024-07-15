package com.microservices.job.service;

import com.microservices.job.dto.JobWithCompanyDTO;
import com.microservices.job.model.Job;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();

    JobWithCompanyDTO findJobById(Long id);
    void createJob(Job job);

    boolean deleteJob(Long id);

    boolean updateJob(Long id, Job job);
}
