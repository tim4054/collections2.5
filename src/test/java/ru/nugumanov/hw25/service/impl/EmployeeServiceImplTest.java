package ru.nugumanov.hw25.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nugumanov.hw25.exceptions.exceptions.*;
import ru.nugumanov.hw25.models.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private final EmployeeServiceImpl service = new EmployeeServiceImpl();

    @Test
    void shouldAddPerson_WhenCorrectParams_ThenAdd() {
        Employee expected = new Employee("Тимур", "Нугуманов", 1, 100.0);

        //test
        Employee actual = service.addPerson("Тимур", "Нугуманов", 1, 100.0);

        //check
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    @DisplayName("Кидает EmployeeNotFoundParameter, когда один из параметров == null")
    void shouldException_WhenNullParams_ThenException(String firstName,
                                                      String lastName,
                                                      Integer department,
                                                      Double salary) {

        Assertions.assertThrows(EmployeeNotFoundParameter.class, () ->
                service.addPerson(firstName, lastName, department, salary));
    }

    @Test
    @DisplayName("Кидает EmployeeStorageIsFullException, когда в коллекцию" +
            "пытаются добавить больше чем 10 объектов")
    void shouldException_WhenMapIsFull_ThenEmployeeStorageISFullException() {

        String firstName = "Тимур";

        //test
        for (int i = 0; i < 10; i++) {
            firstName += "a"; //заполняем коллекцию "уникальными" именами
            service.addPerson(firstName, "Нугуманов", 1, 100.0);
        }

        //check
        Assertions.assertThrows(EmployeeStorageIsFullException.class, () ->
                service.addPerson("Тимур", "Нугуманов", 1, 100.0));
    }

    @Test
    @DisplayName("Кидает EmployeeAlreadyAddedException, когда в коллекцию " +
            "пытаются добавить уже имеющегося сотрудника")
    void shouldException_WhenEmployeeAlreadyAdded_ThenEmployeeAlreadyAddedException() {

        //test
        service.addPerson("Тимур", "Нугуманов", 1, 100.0);

        //check
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () ->
                service.addPerson("Тимур", "Нугуманов", 1, 100.0));
    }

    @Test
    void shouldDeletePerson_WhenCollectionContainsEmployee_ThenDelete() {

        //Добавляю тестового сотрудника,чтобы не вылетел MapsIsEmptyException
        service.addPerson("Test", "Test", 1, 100.0);

        //test
        Employee employee = service.addPerson("Тимур", "Нугуманов", 1, 100.0);
        service.deletePerson("Тимур", "Нугуманов");
        boolean expected = false;
        boolean actual = service.showAll().contains(employee);

        //check
        assertEquals(expected, actual);
    }

    @Test
    void deletePersonException_WhenMapIsEmpty_ThenMapIsEmptyException() {

        Assertions.assertThrows(MapIsEmptyException.class, () -> service.deletePerson("Тимур", "Нугуманов"));
    }

    @Test
    void DeletePersonException_WhenCollectionDoesNotContainsEmployee_ThenEmployeeNotFoundException() {

        //Добавляю тестового сотрудника,чтобы не вылетел MapsIsEmptyException
        service.addPerson("Test", "Test", 1, 100.0);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> service.deletePerson("Тимур", "Нугуманов"));
    }


    @Test
    void shouldFindPerson_WhenCorrectParams_ThenFindPerson() {

        Employee expected = service.addPerson("Тимур", "Нугуманов", 1, 100.0);

        //test
        Employee actual = service.findPerson("Тимур", "Нугуманов");

        //check
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findPersonException_When_ThenEmployeeNotFoundException() {

        //Добавляю тестового сотрудника,чтобы не вылетел MapsIsEmptyException
        service.addPerson("Test", "Test", 1, 100.0);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> service.findPerson("Тимур", "Нугуманов"));
    }

    @Test
    void showAll() {
        Collection<Employee> expected = new ArrayList<>(List.of(
                new Employee("Тимур", "Нугуманов", 1, 100.0),
                new Employee("Тест", "Тестов", 1, 100.0)
        ));

        service.addPerson("Тимур", "Нугуманов", 1, 100.0);
        service.addPerson("Тест", "Тестов", 1, 100.0);

        //test
        Collection<Employee> actual = service.showAll();

        //check
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void checkEmptyMap() {
    }

    @Test
    void checkNotParameter() {
    }

    @Test
    void checkNotParameterInAddMethod() {
    }

    @ParameterizedTest
    @MethodSource("incorrectNames")
    void employeeFixName(String incorrectName) {

        String expected = "Тимур";

        String actual = service.employeeFixName(incorrectName);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> incorrectNames() {

        return Stream.of(
                Arguments.of("тимур"),
                Arguments.of("тИМУР")
        );
    }

    public static Stream<Arguments> argumentsStream() {
        String firstName = "Тимур";
        String lastName = "Нугуманов";
        Integer department = 1;
        Double salary = 100.0;
        return Stream.of(
                Arguments.of(null, lastName, department, salary),
                Arguments.of(firstName, null, department, salary),
                Arguments.of(firstName, lastName, null, salary),
                Arguments.of(firstName, lastName, department, null)
        );
    }
}