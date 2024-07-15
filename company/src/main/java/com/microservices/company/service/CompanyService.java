package com.microservices.company.service;


import com.microservices.company.dto.CompanyWithReviewsDTO;
import com.microservices.company.model.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyWithReviewsDTO> getAll();

    CompanyWithReviewsDTO findCompanyById(Long id);

    void createCompany(Company company);
    boolean deleteCompany(Long id);

    boolean updateCompany(Long id, Company company);


}
