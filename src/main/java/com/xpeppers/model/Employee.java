package com.xpeppers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private final String name;
    private final String surname;
    private final LocalDate birthDate;

    public Employee(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public boolean isBirthday(LocalDate date) {
        return date.getDayOfYear() == birthDate.getDayOfYear();
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return surname;
    }
}
