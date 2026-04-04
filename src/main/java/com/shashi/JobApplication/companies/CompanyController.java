package com.shashi.JobApplication.companies;

import com.shashi.JobApplication.companies.impl.CompanyServiceImple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService =companyService;
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }

    @PostMapping("/createCompany")
    public void createCompany(@RequestBody Company company){
        companyService.create(company);
    }
    @GetMapping("/getCompanyById/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        return new ResponseEntity<>(companyService.getById(id),HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{id}")
        public void deleteCompanyById(@PathVariable Long id) {
        boolean deleted = companyService.deleteById(id);
        if (deleted) {
            new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        } else {
            new ResponseEntity<>("Company NOT FOUND", HttpStatus.NOT_FOUND);

        }

    }

    @PutMapping("/updateCompany/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company){
        boolean updated= companyService.update(id,company);
        if(updated){
            return new ResponseEntity<>(companyService.getById(id), HttpStatus.OK);
    }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   public ResponseEntity<Company> getCompany(Long id){
       Company company= companyService.getById(id);
        if(company!=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
   }
}
