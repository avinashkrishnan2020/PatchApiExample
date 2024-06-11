package com.patch.patchdetails.service;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Service;

import com.patch.patchdetails.entity.Employee;
import com.patch.patchdetails.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee addNewEmployee(Employee employee){
        return employeeRepository.save(employee);
    }


    public Employee putEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee patchDetails(Employee patchedEmployee, Integer id){
        Employee savedEmployee = employeeRepository.findById(id).orElse(null);
        patchEmployeeDetails(savedEmployee, patchedEmployee);
        return employeeRepository.save(savedEmployee);  
    }

    public void patchEmployeeDetails(Employee savedEmployee, Employee patchedEmployee){
        Field[] employeeFields = patchedEmployee.getClass().getDeclaredFields();

        for(Field field: employeeFields){
            field.setAccessible(true);
            
            try {
                Object value = field.get(patchedEmployee);
                if(value != null){
                    field.set(savedEmployee, value);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
            
        }
    }
    
}
