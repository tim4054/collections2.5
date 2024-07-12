package ru.nugumanov.hw25;

import org.springframework.stereotype.Service;
import ru.nugumanov.hw25.exceptions.exceptions.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static List<Employee> persons = new ArrayList<>();
    private static int MAX_VALUE = 10;

    public EmployeeService() {
    }

    public String addPerson(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
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
        return "Сотрудник добавлен " + firstName + " " + lastName;
    }

    public String deletePerson(String firstName, String lastName) {
        Employee employee = null;
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
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
        return firstName + " " + lastName + " удален";
    }

    public Employee findPerson(String firstName, String lastName) {
        //notParameter(firstName,lastName);
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
        Employee result = null;
        if (persons.isEmpty()) {
            throw new PersonsIsEmptyExceptoin();
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
        }
        return result;
    }

    public String showAll() {
        if (persons.isEmpty()) {
            throw new PersonsIsEmptyExceptoin();
        } else {
            return persons.toString();
        }
    }

    /*private static void notParameter(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
    }*/

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

