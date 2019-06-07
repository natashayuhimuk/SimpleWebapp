package com.mastery.task.controller.impl;

import com.mastery.task.Util.BadRequestException;
import com.mastery.task.Util.OrderExistException;
import com.mastery.task.Util.OrderNotFoundException;
import com.mastery.task.controller.EmployeeController;
import com.mastery.task.dao.model.Employee;
import com.mastery.task.service.EmployeeService;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployees() {

        List<Employee> employees;

        try {
            employees = employeeService.getAllEmployees();
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException("Employees not found!", e);
        }

        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {


        if(employeeService.getAllEmployees().equals(employee)){
            throw new OrderExistException("Employee is exists", new RuntimeException());
        }

        try {
            employeeService.createEmployee(employee);
        } catch (PropertyValueException e) {
           throw new BadRequestException("Unable to add employee ", e);
        }

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee currentEmployee;


        System.out.println(employee);

        try {
            currentEmployee = employeeService.findEmployeeById(id);

            currentEmployee.setGender(employee.getGender());
            currentEmployee.setDepartmentId(employee.getDepartmentId());
            currentEmployee.setFirstName(employee.getFirstName());
            currentEmployee.setJobTitle(employee.getJobTitle());
            currentEmployee.setLastName(employee.getLastName());

            employeeService.updateEmployee(currentEmployee);
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException("Unable to find. Employee with id " + employee.getEmployeeId() + " not found", e);
        } catch (PropertyValueException e) {
            throw new BadRequestException("Unable to update employee", e);
        }

        return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Long id) {
        Employee employeeForDelete;
        List<Employee> employees;

        try {
            employeeForDelete = employeeService.findEmployeeById(id);
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException("Employee with id " + id + " not found", e);
        }

        employeeService.deleteEmployee(employeeForDelete);
        employees = employeeService.getAllEmployees();

        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findEmployeeById(@PathVariable("id") Long id) {

        Employee employee;

        try {
            employee = employeeService.findEmployeeById(id);
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException("Employee with id " + id + " not found", e);
        }

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
}
