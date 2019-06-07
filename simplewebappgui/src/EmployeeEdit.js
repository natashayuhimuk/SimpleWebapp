import React, {Component} from 'react';
import {Link, withRouter} from "react-router-dom";
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';

class EmployeeEdit extends Component {
    emptyItem = {
        firstName: '',
        lastName: '',
        departmentId: '',
        jobTitle: '',
        gender: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const employee = await (await fetch(`/employee/get/${this.props.match.params.id}`)).json();
            this.setState({item: employee});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item})
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
        let url;
        let meth;
        if(item.employeeId){
            url = `/employee/${item.employeeId}`;
            meth = 'PUT';
        } else {
            url = `/employee/create`;
            meth = 'POST';
        }

        let init = {
            method: meth ,
            mode: 'cors',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        };
        const res = await (await fetch(url, init)).json();
        this.setState({
            item: res
        });
        this.props.history.push('/employees');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{(item.employeeId) ? 'Edit Employee' : 'Add Employee'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="firstName">First name</Label>
                        <Input type="text" name="firstName" id="firstName" value={item.firstName || ''}
                               onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="firstName">Last name</Label>
                        <Input type="text" name="lastName" id="lastName" value={item.lastName || ''}
                               onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="departmentId">Department id</Label>
                        <Input type="text" name="departmentId" id="departmentId" value={item.departmentId || ''}
                               onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="jobTitle">Job title</Label>
                        <Input type="text" name="jobTitle" id="jobTitle" value={item.jobTitle || ''}
                               onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="gender">Gender</Label>
                        <Input type="text" name="gender" id="gender" value={item.gender || ''}
                               onChange={this.handleChange} />
                    </FormGroup>

                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/employees">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(EmployeeEdit);