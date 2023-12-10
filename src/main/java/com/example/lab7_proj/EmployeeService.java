package com.example.lab7_proj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addToCart(Long id) {
        Employee existingItem = employeeRepository.findById(id).orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            employeeRepository.save(existingItem);
        }
    }

    public void removeFromCart(Long id) {
        Employee existingItem = employeeRepository.findById(id).orElse(null);

        if (existingItem != null && existingItem.getQuantity() > 0) {
            existingItem.setQuantity(existingItem.getQuantity() - 1);

            if (existingItem.getQuantity() == 0) {
                employeeRepository.delete(existingItem);
            } else {
                employeeRepository.save(existingItem);
            }
        }
    }
}
