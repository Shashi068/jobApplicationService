package com.shashi.JobApplication.companies.impl;

import com.shashi.JobApplication.companies.Company;
import com.shashi.JobApplication.companies.CompanyRepo;
import com.shashi.JobApplication.companies.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImple implements CompanyService {
    CompanyRepo companyRepo;

     public CompanyServiceImple(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public void create(Company company) {
        companyRepo.save(company);
    }

    @Override
    public Company getById(Long id) {
        return companyRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if(companyRepo.existsById(id)){
            companyRepo.deleteById(id);
           return true;
    }else {
            return false;
        }
    }

    @Override
    public boolean update(Long id, Company company) {
        return companyRepo.findById(id).map(existingCompany -> {
            existingCompany.setName(company.getName());
            existingCompany.setDescription(company.getDescription());
            companyRepo.save(existingCompany);
            return true;
        }).orElse(false);
    }
}
