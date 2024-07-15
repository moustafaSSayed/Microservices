package com.microservices.job.service.Implementation;

import com.microservices.job.dto.CompanyWithReviewsDTO;
import com.microservices.job.dto.JobWithCompanyDTO;
import com.microservices.job.external.Company;
import com.microservices.job.model.Job;
import com.microservices.job.repository.JobRepository;
import com.microservices.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();
        return jobs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobWithCompanyDTO findJobById(Long id) {

        Job job = jobRepository.findById(id).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public void createJob(Job job) {

        jobRepository.save(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Optional<Job> tempJob = jobRepository.findById(id);
        if (tempJob.isPresent()) {
            Job updatedJob = tempJob.get();
            updatedJob.setTitle(job.getTitle());
            updatedJob.setDescription(job.getDescription());
            updatedJob.setMinSalary(job.getMinSalary());
            updatedJob.setMaxSalary(job.getMaxSalary());
            updatedJob.setLocation(job.getLocation());
            jobRepository.save(updatedJob);
            return true;
        }
        return false;

    }

    private JobWithCompanyDTO convertToDTO (Job job) {

        //RestTemplate restTemplate = new RestTemplate();
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId()
                                                    , CompanyWithReviewsDTO.class).getCompany();
        jobWithCompanyDTO.setJob(job);
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
