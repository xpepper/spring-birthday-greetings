package com.xpeppers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xpeppers.model.Employee;
import com.xpeppers.repository.EmployeeRepository;

import java.time.LocalDate;

@Service("birthdayService")
public class BirthdayService {

    private EmployeeRepository repository;
    private GreetingSender sender;

    @Autowired
    public BirthdayService(EmployeeRepository repository, GreetingSender sender) {
        this.repository = repository;
        this.sender = sender;
    }

    public void send(LocalDate date) {
        for (Employee employee : repository.findAll()) {
            if(employee.isBirthday(date))
                sender.send(employee);
        }
    }
}