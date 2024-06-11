package com.patch.patchdetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patch.patchdetails.entity.Employee;
import com.patch.patchdetails.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {
    

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping(path="{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") Integer employeeId){
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee){
        return new ResponseEntity<>(employeeService.addNewEmployee(newEmployee), HttpStatus.OK);
    }

    @PatchMapping(path="{employeeId}")
    public ResponseEntity<Employee> patchEmployeeDetails(@RequestBody Employee employee, @PathVariable("employeeId") Integer employeeId){
        return new ResponseEntity<>(employeeService.patchDetails(employee, employeeId), HttpStatus.OK);
    }

    @PutMapping(path={"employeeId"})
    public ResponseEntity<Employee> putEmployeeDetails(@RequestBody Employee employee, @PathVariable("employeeId") Integer employeeId){
        return new ResponseEntity<>(employeeService.putEmployee(employee), HttpStatus.OK);
    }
}