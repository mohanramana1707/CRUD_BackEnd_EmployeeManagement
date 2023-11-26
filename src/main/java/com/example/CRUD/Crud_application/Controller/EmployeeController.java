package com.example.CRUD.Crud_application.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUD.Crud_application.Entity.Employee;
import com.example.CRUD.Crud_application.Exception.ResourceNotFoundException;
import com.example.CRUD.Crud_application.Repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@PostConstruct
	public void employeeinitialize() {
		List<Employee> el= new ArrayList<>();
		el.add(new Employee(1,"mohan","ram","mohan@gmail.com"));
		el.add(new Employee(2,"Ajith","vijay","Ajith@gmail.com"));
		el.add(new Employee(3,"dhoni","thala","dhoni@gmail.com"));
		
		empRepo.saveAll(el);
		
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> l=empRepo.findAll();
		System.out.println(l.toString());
		return new ResponseEntity<List<Employee>>(l, HttpStatus.OK);
	}
	
	@PostMapping("/addEmployee")
	public Employee createEmployee(@RequestBody Employee emp) {
		return empRepo.save(emp);
	}

//get employee by ID	
	@GetMapping("/employee/{empId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empId) {
		
	Employee emp=empRepo.findById(empId)
						.orElseThrow(()-> new ResourceNotFoundException("Employee not exists for Id : "+empId));
		
	return  new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	
//update EMployee
	@PutMapping("/update/{empId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long empId ,@RequestBody Employee updEmp) {
		
		Employee retEmp=empRepo.findById(empId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not exists for Id : "+empId));
		
		retEmp.setFirstName(updEmp.getFirstName());
		retEmp.setlastName(updEmp.getlastName());
		retEmp.setEmailId(updEmp.getEmailId());
		
		Employee updateEmp=empRepo.save(retEmp);
		
		return  new ResponseEntity<Employee>(updateEmp,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteEmployee/{empId}")
	public ResponseEntity<Employee>  deleteEmployee(@PathVariable long empId){
		
		Employee delEmp=empRepo.findById(empId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not exists for Id : "+empId));
		
		empRepo.delete(delEmp);
		
		return new ResponseEntity<Employee>(delEmp,HttpStatus.OK);
	}
	
	
	
	
}
