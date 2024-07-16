package ru.nugumanov.hw25;

import org.springframework.stereotype.Service;
import ru.nugumanov.hw25.exceptions.exceptions.*;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> persons;
    private final int MAX_VALUE = 10;

    public EmployeeService(Map<String, Employee> persons) {
        this.persons = persons;
    }

    public Employee addPerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
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

    public Employee deletePerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        notParameter(firstName, lastName);
        checkEmptyMap();
        if (persons.containsKey(String.format(firstName+lastName))) {
            persons.remove(String.format(firstName+lastName));
        } else {
            throw new EmployeeNotFoundException();
        }
        return person;
    }

    public Employee findPerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        notParameter(firstName, lastName);
        checkEmptyMap();
        if (persons.containsKey(String.format(firstName+lastName))) {
            return person;
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> showAll() {
        checkEmptyMap();
        return Collections.unmodifiableMap(persons).values();

    }

    private void notParameter(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
    }

    private void checkEmptyMap() {
        if (persons.isEmpty()) {
            throw new EmployeeNotFoundParameter();
        }
    }

    public static void main(String[] args) {
        Map<String, Employee> employees = new HashMap<>();
        EmployeeService employeeService = new EmployeeService(employees);
        employeeService.addPerson("Тимур", "Нугуманов");
        employeeService.addPerson("Тимур", "Нугуманов");
        employeeService.addPerson("Юлия", "Нугуманова");
        employeeService.deletePerson("Юлия", "Нугуманова");
        System.out.println(employeeService.findPerson("Тимур", "Нугуманов"));

        System.out.println(employees);
    }
}

