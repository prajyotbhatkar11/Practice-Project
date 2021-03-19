package com.practice.example.RestPractice.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDAOService {
private int id;
    private static List<Employee> employees = Arrays.asList();
    private int employeeCount=100000;

    public static List<Employee> findAll(){
        return employees;
    }

    public Employee findByID(int employee_ID){
        for (Employee emp : employees){
            if (emp.getEmployee_ID() == employee_ID){
                return emp;
            }
        }
        return null;
    }

    public Employee save(Employee employee){

        if (employee.getEmployee_ID() == null){
            employee.setEmployee_ID(++employeeCount);
        }
        employees.add(employee);
         //employees.stream().filter(employee1 -> employee1.getEmployee_ID() == null).collect(Collectors.toList());
        return null;
    }

    //delete
    public Employee deleteByID(int employee_ID){

        employees.removeIf(employee -> employee.getEmployee_ID()==employee_ID);
        return null;
    }
    
    public Employee updateSalary(int employee_ID, double newSalary){
        for (Employee emp:employees){
            if (emp.getEmployee_ID()==employee_ID){
                emp.updateSalary(newSalary);
            }
        }
        return null;
    }
}