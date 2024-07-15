package com.microservices.company.service.Implementation;

import com.microservices.company.dto.CompanyWithReviewsDTO;
import com.microservices.company.external.Review;
import com.microservices.company.model.Company;
import com.microservices.company.repository.CompanyRepository;
import com.microservices.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    @Autowired
    RestTemplate restTemplate;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyWithReviewsDTO> getAll() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyWithReviewsDTO> companyWithReviewsDTOs = new ArrayList<>();
        return companies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyWithReviewsDTO findCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        return convertToDTO(company);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
            Optional<Company> oldCompany = companyRepository.findById(id);
            if(oldCompany.isPresent()) {
                Company updatedCompany = oldCompany.get();
                updatedCompany.setDescription(company.getDescription());
                updatedCompany.setName(company.getName());
                companyRepository.save(updatedCompany);
                return true;
            }
            else
                return false;
    }

    private CompanyWithReviewsDTO convertToDTO (Company company) {

        //RestTemplate restTemplate = new RestTemplate();
        CompanyWithReviewsDTO companyWithReviewsDTO = new CompanyWithReviewsDTO();
        Review[] reviews = restTemplate.getForObject("http://review-service:8083/reviews?companyId=" + company.getId(), Review[].class);
        companyWithReviewsDTO.setCompany(company);
        companyWithReviewsDTO.setReviews(reviews != null ? List.of(reviews) : new ArrayList<>());

        return companyWithReviewsDTO;
    }
}
