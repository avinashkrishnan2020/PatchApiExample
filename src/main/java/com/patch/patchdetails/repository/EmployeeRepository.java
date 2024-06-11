package com.patch.patchdetails.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.patch.patchdetails.entity.Employee;

@Repository
public class EmployeeRepository {

    private Map<Integer, Employee> employeeMap;

    public EmployeeRepository(){
        employeeMap = new HashMap<>();
    }

    public Employee save(Employee employee){
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public Optional<Employee> findById(Integer id){
        return Optional.of(employeeMap.get(id));
    }

    public List<Employee> findAll(){
        return this.employeeMap.entrySet()
            .stream()
            .map(entry -> entry.getValue())
            .collect(Collectors.toList());
    }
    
    
}
