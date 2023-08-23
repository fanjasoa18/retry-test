package com.example.prog4.service;

import com.example.prog4.repository.cnaps.CnapsEmployeeRepository;
import com.example.prog4.repository.cnaps.entity.CnapsEmployee;
import com.example.prog4.repository.employee.EmployeeRepository;
import com.example.prog4.repository.employee.entity.Employee;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeFacade {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CnapsEmployeeRepository cnapsEmployeeRepository;

    public Employee getEmployeeById(String id) {

        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createOrUpdateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
