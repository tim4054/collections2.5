package ru.nugumanov.hw25.service.impl;

import org.springframework.stereotype.Service;
import ru.nugumanov.hw25.exceptions.exceptions.*;
import ru.nugumanov.hw25.models.Employee;
import ru.nugumanov.hw25.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> persons;
    private final int MAX_VALUE = 10;

    public EmployeeServiceImpl(Map<String, Employee> persons) {
        this.persons = persons;
    }

    @Override
    public Employee addPerson(String firstName, String lastName, int department, double salary) {
        Employee person = new Employee(firstName, lastName, salary, department);
        notParameter(firstName, lastName);

        if (MAX_VALUE <= persons.size()) {
            throw new EmployeeStorageIsFullException();
        }
        if (persons.containsKey(person)) {
            throw new EmployeeAlreadyAddedException();
        }
        persons.put(String.format(firstName + lastName), person);
        return person;
    }

    @Override
    public Employee deletePerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        notParameter(firstName, lastName);
        checkEmptyMap();
        if (persons.containsKey(String.format(firstName + lastName))) {
            persons.remove(String.format(firstName + lastName));
        } else {
            throw new EmployeeNotFoundException();
        }
        return person;
    }

    @Override
    public Employee findPerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        notParameter(firstName, lastName);
        checkEmptyMap();
        if (persons.containsKey(String.format(firstName + lastName))) {
            return person;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> showAll() {
        checkEmptyMap();
        return Collections.unmodifiableMap(persons).values();

    }

    public void checkEmptyMap() {
        if (persons.isEmpty()) {
            throw new EmployeeNotFoundParameter();
        }
    }

    public void notParameter(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
    }
}

