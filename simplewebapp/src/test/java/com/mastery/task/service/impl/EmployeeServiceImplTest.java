package com.mastery.task.service.impl;


import com.mastery.task.config.DataConfig;
import com.mastery.task.dao.model.Employee;
import com.mastery.task.dao.model.Gender;
import com.mastery.task.service.EmployeeService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class})
public class EmployeeServiceImplTest {

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;

    @Autowired
    private EmployeeService employeeService;

    @Before
    public void setUp() throws Exception{
        employee1 = new Employee( "FirstName1", "LastName1", 1236, "cook", Gender.MALE);
        employee2 = new Employee( "FirstName2", "LastName2", 56, "programmer", Gender.MALE);
        employee3 = new Employee( "FirstName3", "LastName3", 74, "economist", Gender.FEMALE);

        employeeService.createEmployee(employee1);
        employeeService.createEmployee(employee2);
        employeeService.createEmployee(employee3);

    }

    @After
    public void setDown() throws Exception{
        employeeService.deleteEmployee(employee1);
        employeeService.deleteEmployee(employee2);
        employeeService.deleteEmployee(employee3);
    }


    @Test
    public void getAllEmployees1() {
        Assert.assertEquals(ArrayList.class, employeeService.getAllEmployees().getClass());
    }

    @Test
    public void getAllEmployees2() {
        Assert.assertEquals(Employee.class, employeeService.getAllEmployees().get(0).getClass());
    }

    @Test
    public void getAllEmployees3() {
        Assert.assertNotNull(employeeService.getAllEmployees().get(0).getGender());
    }

    @Test
    public void getAllEmployees4() {
        Assert.assertNotNull(employeeService.getAllEmployees().get(0));
    }
    @Test
    public void getAllEmployees5() {
        Assert.assertNotEquals(null, employeeService.getAllEmployees().get(0));
    }


    @Test
    public void createEmployee1() {
        Assert.assertEquals(employee1, employeeService.createEmployee(employee1));
    }

    @Test
    public void createEmployee2() {
        Assert.assertEquals(Employee.class, employeeService.createEmployee(employee2).getClass());
    }

    @Test
    public void createEmployee3() {
        Assert.assertNotNull(employeeService.createEmployee(employee3));
    }

    @Test
    public void createEmployee4() {
        Assert.assertNotEquals(List.class, employeeService.createEmployee(employee2));
    }

    @Test
    public void createEmployee5() {
        Assert.assertNotEquals(null, employeeService.createEmployee(employee1).getGender());
    }


    @Test
    public void updateEmployee1() {
        Assert.assertEquals(employee1, employeeService.updateEmployee(employee1));
    }

    @Test
    public void updateEmployee2() {
        Assert.assertEquals(Employee.class, employeeService.updateEmployee(employee1).getClass());
    }

    @Test
    public void updateEmployee3() {
        Assert.assertNotNull(employeeService.updateEmployee(employee2));
    }

    @Test
    public void updateEmployee4() {
        Assert.assertNotEquals(List.class, employeeService.updateEmployee(employee3));
    }

    @Test
    public void updateEmployee5() {
        Assert.assertNotEquals(null, employeeService.updateEmployee(employee2).getEmployeeId());
    }

    @Test
    public void deleteEmployee() {
       employeeService.deleteEmployee(employee1);
        if (employeeService.getAllEmployees().equals(employee1)){
            Assert.fail();
        }
    }

    @Test
    public void findEmployeeById1() {
        Assert.assertNotNull(employeeService.findEmployeeById(employee1.getEmployeeId()));
    }

    @Test
    public void findEmployeeById2() {
        Assert.assertNotEquals(List.class, employeeService.findEmployeeById(employee1.getEmployeeId()));
    }

    @Test
    public void findEmployeeById3() {
        Assert.assertEquals(employee1, employeeService.findEmployeeById(employee1.getEmployeeId()));
    }

    @Test
    public void findEmployeeById4() {
        Assert.assertEquals(Employee.class, employeeService.findEmployeeById(employee1.getEmployeeId()).getClass());
    }

    @Test
    public void findEmployeeById5() {
        Assert.assertNotEquals(null, employeeService.findEmployeeById(employee1.getEmployeeId()).getDepartmentId());
    }
}