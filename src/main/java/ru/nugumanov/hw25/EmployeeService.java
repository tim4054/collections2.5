package ru.nugumanov.hw25;

import org.springframework.stereotype.Service;
import ru.nugumanov.hw25.exceptions.exceptions.EmployeeAlreadyAddedException;
import ru.nugumanov.hw25.exceptions.exceptions.EmployeeNotFoundException;
import ru.nugumanov.hw25.exceptions.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService extends Employee {
    private static List<Employee> persons = new ArrayList<>();
    private static int MAX_VALUE = 10;

    public EmployeeService(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public String addPerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        if (MAX_VALUE <= persons.size()) {
            throw new EmployeeStorageIsFullException();
        }
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getFirstName().equals(firstName) &&
                    persons.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        persons.add(0, person);
        return "Сотрудник добавлен" + firstName + lastName;
    }

    public void deletePerson(String firstName, String lastName) {
        Employee employee = null;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getFirstName().equals(firstName) &&
                    persons.get(i).getLastName().equals(lastName)) {
                employee = persons.get(i);
            }
        }
        if (employee != null) {
            persons.remove(employee);
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee findPerson(String firstName, String lastName) {
        Employee result = null;
        if (persons.isEmpty()) {
            System.out.println("Нет сотрудников");
            ;
        }
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getFirstName().equals(firstName) &&
                    persons.get(i).getLastName().equals(lastName)) {
                result = persons.get(i);
            } else {
                result = null;
            }
        }
        if (result == null) {
            throw new EmployeeNotFoundException();
        } else {
            System.out.println(result);
        }
        return result;
    }

    public static void main(String[] args) {
        /*EmployeeService.addPerson("Тим", "Нуг1");
        EmployeeService.addPerson("Тим", "Нуг2");
        EmployeeService.addPerson("Тим", "Нуг3");
        EmployeeService.addPerson("Тим", "Нуг4");
        EmployeeService.addPerson("Тим", "Нуг5");
        EmployeeService.addPerson("Тим", "Нуг6");
        EmployeeService.addPerson("Тим", "Нуг7");
        EmployeeService.addPerson("Тим", "Нуг8");
        EmployeeService.addPerson("Тим", "Нуг9");
        EmployeeService.addPerson("Тим", "Нуг10");


        EmployeeService.deletePerson("Тим", "Нуг10");
        EmployeeService.deletePerson("Тим", "Нуг9");
        EmployeeService.deletePerson("Тим", "Нуг8");
        EmployeeService.findPerson("Тим", "Нуг");
        System.out.println(persons);
        System.out.println(persons.size());*/
    }
}

