package com.rest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Employee;
import com.rest.models.EmployeeRequest;
import com.rest.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {
		logger.info("Employee Request is" + employeeRequest.toString());
		try {
			Employee employee = employeeService.saveEmployee(employeeRequest);
			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception while Saving to db" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAllEmployee() {

		try {
			List<Employee> employees = employeeService.getAllEmployee();
			return new ResponseEntity<>(employees, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Exception while getting from db" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getOneEmployee(@PathVariable(value = "id") Integer id) {
		logger.info("Employye Id from URI is" + id);
		try {
			Employee employee = employeeService.getEmployee(id);
			return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Exception while getting from db" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Integer id,@RequestBody EmployeeRequest employeeRequest){
		logger.info("Employye Id from URI is" + id);
		logger.info("Employye Request" + employeeRequest.toString());
		
		try {
			Employee updateEmployee = employeeService.updateEmployee(id, employeeRequest);
			return new ResponseEntity<>(updateEmployee, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception while Updating to db" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
  
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletEmployee(@PathVariable(value = "id") Integer id) {
		logger.info("Employye Id from URI is" + id);
		try {
			Employee employee = employeeService.deleteEmployee(id);
			return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Exception while deleting from db" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

}
