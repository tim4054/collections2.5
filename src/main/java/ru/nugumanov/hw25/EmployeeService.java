package ru.nugumanov.hw25;

import org.springframework.stereotype.Service;
import ru.nugumanov.hw25.exceptions.exceptions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> persons = new ArrayList<>();
    private final int MAX_VALUE = 10;

    public EmployeeService() {
    }

    public Employee addPerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        notParameter(firstName, lastName);

        if (MAX_VALUE <= persons.size()) {
            throw new EmployeeStorageIsFullException();
        }
        if (persons.contains(person)) {
            throw new EmployeeAlreadyAddedException();
        }
        persons.add(0, person);
        return person;
    }

    public Employee deletePerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        notParameter(firstName, lastName);
        checkEmptyList();
        if (persons.contains(person)) {
            persons.remove(person);
        } else {
            throw new EmployeeNotFoundException();
        }
        return person;
    }

    public Employee findPerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        notParameter(firstName, lastName);
        checkEmptyList();
        if (persons.contains(person)) {
            return person;
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> showAll() {
        checkEmptyList();
        return Collections.unmodifiableList(persons);

    }

    private void notParameter(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
    }

    private  void checkEmptyList() {
        if (persons.isEmpty()) {
            throw new EmployeeNotFoundParameter();
        }
    }
}

