package com.xpeppers.repository;

import org.springframework.data.repository.CrudRepository;
import com.xpeppers.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
