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
    public Employee addPerson(String firstName, String lastName, int department, double salary) {
        Employee person = new Employee(fixString(firstName),
                fixString(lastName),
                salary,
                department);
        checkNotParameter(firstName, lastName);

        if (MAX_VALUE <= persons.size()) {
            throw new EmployeeStorageIsFullException();
        }
        if (persons.containsKey(person)) {
            throw new EmployeeAlreadyAddedException();
        }
        persons.put(String.format(fixString(firstName) + fixString(lastName)), person);
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
        Employee person = new Employee(firstName, lastName);
        checkNotParameter(firstName, lastName);
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
            throw new MapIsEmptyException();
        }
    }

    public void checkNotParameter(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundParameter();
        }
    }

    public String fixString(String string) {
        //1-й способ:
        /* String validSymbols = "qwertyuiopasdfghjklzxcvbnm" +
                "QWERTYUIOPASDFGHJKLZXCVBNM" +
                "йцукенгшщзхъфывапролджэячсмитьбю" +
                "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ-";

        String cleanedString = StringUtils.replaceChars(string, validSymbols, "");
        if (!cleanedString.isBlank()) {
            throw new RuntimeException("Bad request 400");
        }*/

        //2-й способ

        if (!string.matches("[a-яА-Яa-zA-Z-]+")) {
            throw new EmployeeBadRequest();
        }

        char[] s = string.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '-') {
                String[] parts = string.split("-");
                return (StringUtils.capitalize(parts[0]) + "-" +
                        StringUtils.capitalize(parts[1]));
            }
        }
        return StringUtils.capitalize(string);
        //Метод fixString() чинит строку: 1) (защита от случайного CapsLock)удалено;
        //                                2) защита от невалидных символов;
        //                                3) работает с двойной фамилией.
    }

    //Test
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.addPerson("тимУр", "нУГУМАНОВ-ИвАнов1", 1, 100);
        System.out.println(employeeService.showAll());

    }
}

