package com.practice2.example.RestPractice2.Employee;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Jobs {
    @Id
    @GeneratedValue
    private Integer id;
    private String jobDescription;

    protected Jobs(){

    }

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @Override
    public String toString() {
        return "Jobs{" +
                "id=" + id +
                ", jobDescription='" + jobDescription + '\'' +
                '}';
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
