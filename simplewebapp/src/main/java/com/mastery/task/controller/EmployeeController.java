package com.mastery.task.controller;

import com.mastery.task.dao.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface EmployeeController {

    ResponseEntity<List<Employee>> getAllEmployees();

    ResponseEntity<?> createEmployee(Employee employee);

    ResponseEntity<Employee> updateEmployee(Long id, Employee employee);

    ResponseEntity<?> deleteEmployeeById(Long id);

    ResponseEntity<?> findEmployeeById(Long id);
}
