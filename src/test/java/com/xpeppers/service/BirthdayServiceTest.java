package com.xpeppers.service;

import com.xpeppers.model.Employee;
import com.xpeppers.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.time.LocalDate.of;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayServiceTest {
    private static final String NAME = "any name";
    private static final String SURNAME = "any surname";

    @InjectMocks
    private BirthdayService service;

    @Mock
    private EmployeeRepository repository;

    @Mock
    private GreetingSender sender;

    @Test
    public void send_greeting_when_is_birthday() throws Exception {
        Employee employee = employeeBornOn(1985, 3, 14);
        when(repository.findAll()).thenReturn(singletonList(employee));

        service.send(of(2017, 3, 14));

        verify(sender).send(employee);
    }

    @Test
    public void does_not_send_greeting_when_is_not_birthday() throws Exception {
        Employee employee = employeeBornOn(1985, 3, 14);
        when(repository.findAll()).thenReturn(singletonList(employee));

        service.send(of(2017, 3, 17));

        verify(sender, never()).send(any());
    }

    private Employee employeeBornOn(int year, int month, int day) {
        return new Employee(NAME, SURNAME, of(year, month, day));
    }
}