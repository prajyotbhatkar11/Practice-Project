package com.practice2.example.RestPractice2.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeJPAResource {

    @Autowired
    private EmployeeReposetory employeeDAOService;

    @Autowired
    private JobsReposetory jobsReposetory;

    @GetMapping(path ="/jpa/employees")
    public List<Employee> retrieveAllEmployee (){
        return employeeDAOService.findAll();
    }

    @GetMapping(path ="/jpa/employees/{id}")
    public EntityModel<Employee> retrieveOne(@PathVariable int id) {
        Optional<Employee> employee = employeeDAOService.findById(id);
        EntityModel<Employee> resource = EntityModel.of(employee.get());
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllEmployee());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @DeleteMapping("/jpa/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeDAOService.deleteById(id);
    }

    @GetMapping("/jpa/employees/{id}/jobs")
    public List<Jobs> getAllJobs(@PathVariable int id){
        Optional<Employee> optionalEmployee = employeeDAOService.findById(id);

        return optionalEmployee.get().getJobs();
    }
    @PostMapping("/jpa/employee")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee){
        Employee saveEmployee = employeeDAOService.save(employee);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveEmployee.getEmployee_ID())
                .toUri();
        return  ResponseEntity.created(location).build();
    }

}
