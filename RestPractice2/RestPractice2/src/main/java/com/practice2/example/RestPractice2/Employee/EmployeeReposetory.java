package com.practice2.example.RestPractice2.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeReposetory extends JpaRepository<Employee, Integer> {
}
