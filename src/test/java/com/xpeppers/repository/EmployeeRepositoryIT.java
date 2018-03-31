package com.xpeppers.repository;

import com.xpeppers.App;
import com.xpeppers.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.collect.Lists.newArrayList;
import static java.time.LocalDate.of;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = App.class)
public class EmployeeRepositoryIT {

    @Autowired
    private EmployeeRepository repository;

    @Before
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void save_an_employee() {
        repository.save(anEmployee());

        assertThat(newArrayList(repository.findAll()), is(not(empty())));

        Iterable<Employee> all = repository.findAll();
        Employee first = all.iterator().next();
        assertThat(first.getName(), is(equalTo("First Name")));
    }

    @Test
    public void delete_an_employee() {
        Employee savedEmployee = repository.save(anEmployee());

        repository.delete(savedEmployee);

        assertThat(newArrayList(repository.findAll()), is(empty()));
    }

    private Employee anEmployee() {
        return new Employee("First Name", "Last Name", of(1985, 3, 14));
    }
}