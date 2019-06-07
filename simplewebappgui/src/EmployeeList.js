import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';


class EmployeeList extends Component{

    constructor(props) {
        super(props);
        this.state = { employees: [], isLoading: true };
        //this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('/employee/')
            .then(response => response.json())
            .then(data => this.setState({employees: data, isLoading: false}));

    }

    async remove(id){
        this.setState({isLoading: true});
        await fetch(`/employee/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(data => {
            alert("delete employee");
            this.setState({employees: data, isLoading: false})
        });
        
    }

    render() {
        const {employees, isLoading} = this.state;

        if(isLoading){
            return <p>Loading...</p>
        }

        const employeeList = employees.map(employee => {
            return <tr key={employee.employeeId}>
                <td style={{whiteSpace: 'nowrap'}}>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.departmentId}</td>
                <td>{employee.jobTitle}</td>
                <td>{employee.gender}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/employee/" + employee.employeeId}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(employee.employeeId)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right" >
                        <Button color="success" tag={Link} to="/employee/new">Create Employee</Button>
                    </div>
                    <h3> Employees </h3>
                    <div>
                    <Table responsive className="mt-4" >
                        <thead>
                        <tr>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Department id</th>
                            <th>Job title</th>
                            <th>Gender</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {employeeList}
                        </tbody>
                    </Table>
                    </div>
                </Container>
            </div>
        );
    }
}

export default EmployeeList;