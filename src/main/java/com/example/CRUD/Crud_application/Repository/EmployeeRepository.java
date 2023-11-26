package com.example.CRUD.Crud_application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRUD.Crud_application.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
