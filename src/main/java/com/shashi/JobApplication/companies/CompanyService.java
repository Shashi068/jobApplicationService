package com.shashi.JobApplication.companies;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void create(Company company);
    Company getById(Long id);
    boolean deleteById(Long id);
    boolean update(Long id, Company company);
}
