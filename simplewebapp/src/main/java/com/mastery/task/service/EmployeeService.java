package com.mastery.task.service;

import com.mastery.task.dao.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    void deleteEmployeeById(Long id);

    Employee findEmployeeById(Long id);
}
