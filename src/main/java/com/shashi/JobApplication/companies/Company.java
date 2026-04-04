package com.shashi.JobApplication.companies;

import com.shashi.JobApplication.job.Job;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

   // private List<Reviews> reviews;
    public Company(){

     }
     public Company(Long id,String name,String description){
      this.id=id;
      this.name=name;
      this.description=description;
   }
   public void setId(Long id){
      this.id=id;
   }
   public Long getId(){
      return id;
   }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
}
