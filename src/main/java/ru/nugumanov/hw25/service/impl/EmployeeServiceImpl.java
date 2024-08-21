package ru.nugumanov.hw25.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.nugumanov.hw25.exceptions.exceptions.*;
import ru.nugumanov.hw25.models.Employee;
import ru.nugumanov.hw25.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> persons;
    private final int MAX_VALUE = 10;

    public EmployeeServiceImpl() {
        this.persons = new HashMap<>(MAX_VALUE);
    }

    @Override
    public Employee addPerson(String firstName,
                              String lastName,
                              Integer department,
                              Double salary) {

        checkNotParameterInAddMethod(firstName, lastName, department, salary);

        Employee person = new Employee(employeeFixName(firstName),
                employeeFixName(lastName),
                department,
                salary);

        if (MAX_VALUE <= persons.size()) {
            throw new EmployeeStorageIsFullException();
        }
        if (persons.containsValue(person)) {
            throw new EmployeeAlreadyAddedException();
        }
        persons.put(String.format(employeeFixName(firstName) + employeeFixName(lastName)), person);
        return person;
    }

    @Override
    public Employee deletePerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);

        checkNotParameter(firstName, lastName);

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

        checkNotParameter(firstName, lastName);

        checkEmptyMap();

        if (persons.containsKey(String.format(firstName + lastName))) {
            return persons.get(firstName + lastName);
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
            throw new MapIsEmptyException();
        }
    }

    public void checkNotParameter(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
    }

    public void checkNotParameterInAddMethod(String firstName,
                                             String lastName,
                                             Integer department,
                                             Double salary) {
        if (firstName == null || lastName == null || department == null || salary == null) {
            throw new EmployeeNotFoundParameter();
        }
    }

    public String employeeFixName(String string) {
        if (!string.matches("[a-яА-Яa-zA-Z-]+")) {
            throw new EmployeeBadRequest();
        }

        char[] s = string.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '-') {
                String[] parts = string.split("-");
                return (StringUtils.capitalize(parts[0].toLowerCase()) + "-" +
                        StringUtils.capitalize(parts[1].toLowerCase()));
            }
        }
        return StringUtils.capitalize(string.toLowerCase());
    }
}

