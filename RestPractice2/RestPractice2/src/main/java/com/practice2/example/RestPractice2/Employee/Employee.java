package com.practice2.example.RestPractice2.Employee;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer employee_ID;

    private String name;

    @Email
    private String email;

    private String department;

    private Double salary;

    public List<Jobs> getJobs() {
        return jobs;
    }

    public void setJobs(List<Jobs> jobs) {
        this.jobs = jobs;
    }

    @OneToMany(mappedBy = "employee")
    private List<Jobs> jobs;

    protected Employee() {

    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_ID=" + employee_ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Integer getEmployee_ID() {
        return employee_ID;
    }

    public void setEmployee_ID(Integer employee_ID) {
        this.employee_ID = employee_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void updateSalary(Double newSalary) {
        this.salary = newSalary;
    }
}
