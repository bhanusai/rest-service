package com.rest.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.controller.EmployeeController;
import com.rest.entity.Employee;
import com.rest.models.EmployeeRequest;
import com.rest.repo.EmployeeRepository;

@Service
public class EmployeeService {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(EmployeeRequest employeeRequest) throws Exception {

		Employee email = employeeRepository.findByEmailId(employeeRequest.getEmailId());
		if (email != null) {
			logger.info("Employe is already exist with email" + employeeRequest.getEmailId());
			return email;
		} else {
			Employee employee = new Employee();
			employee.setFirstName(employeeRequest.getFirstName());
			employee.setLastName(employeeRequest.getLastName());
			employee.setEmailId(employeeRequest.getEmailId());
			employee.setGender(employeeRequest.getGender());
			employee.setAge(employeeRequest.getAge());
			employee.setAddress(employeeRequest.getAddress());

			Employee employeeresponse = employeeRepository.save(employee);

			logger.info("Employee saved to Database with Id is " + employeeresponse.getId());
			return employeeresponse;

		}
	}

	public List<Employee> getAllEmployee() throws Exception {

		return employeeRepository.findAll();
	}

	public Employee getEmployee(Integer id) throws Exception {

		return employeeRepository.findById(id).get();

	}

	public Employee updateEmployee(Integer id, EmployeeRequest employeeRequest) throws Exception {

		Employee employee = employeeRepository.findById(id).get();
		if (employee != null) {
			employee.setFirstName(employeeRequest.getFirstName());
			employee.setLastName(employeeRequest.getLastName());
			employee.setEmailId(employeeRequest.getEmailId());
			employee.setGender(employeeRequest.getGender());
			employee.setAge(employeeRequest.getAge());
			employee.setAddress(employeeRequest.getAddress());
			employeeRepository.save(employee);
			return employee;
		} else {
			logger.info("Employee Not found in DB " + id);

		}
		return null;
	}
	
	public Employee deleteEmployee(Integer id) throws Exception {
		
		Employee employee = employeeRepository.findById(id).get();
		if(employee != null) {
        employee.setIsdeleted(true);
        Employee softdeleteEmployee = employeeRepository.save(employee);
        return softdeleteEmployee;
		}
		else {
			logger.info("Employee Not found in DB " + id);
		}
		return null;
	}
}
