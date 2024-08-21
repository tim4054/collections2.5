package ru.nugumanov.hw25.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.nugumanov.hw25.exceptions.exceptions.EmployeeNotFoundException;
import ru.nugumanov.hw25.models.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static final Collection<Employee> employees = List.of(
            new Employee("Тимур", "Нугуманов", 1, 100.0),
            new Employee("Алексей", "Петров", 2, 200.0),
            new Employee("Сергей", "Иванов", 3, 300.0));

    @Test
    void getSalaryByDepartment_WhenCorrectData_ThenReturnCorrectSalarySum() {
        when(employeeService.showAll()).thenReturn(employees);
        int department = 1;
        double expected = 0;

        for (Employee employee : employees) {
            if (department == employee.getDepartment()) {
                expected += employee.getSalary();
            }
        }

        //test
        double actual = departmentService.getSalaryByDepartment(1);

        //check
        assertEquals(expected, actual);
    }

//    Извиняюсь за закомеченный код, но не могу его убрать, поскольку это очень полезный рецепт для меня,
//    но, не актульный, поскольку в DepartmentService не может прилететь пустая мапа.
//    В EmployeeService в методе showAll() есть вложенный метод checkEmptyMap, который выкенет Exception.

//    @Test
//    void getSalaryByDepartment_WhenEmptyMap_ThenReturnZero() {
//        when(employeeService.showAll()).thenReturn(new ArrayList<>());
//
//        //test
//        double actual = departmentService.getSalaryByDepartment(1);
//
//        //check
//        assertThat(actual).isZero();
//    }

    @Test
    void getMinSalaryByDepartment_WhenCorrectData_ThenReturnCorrectMinSalary() {
        when(employeeService.showAll()).thenReturn(employees);
        int department = 1;

        Employee expected = employeeService.showAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);

        //test
        Employee actual = departmentService.getMinSalaryByDepartment(department);

        //check
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getMinSalaryByDepartment_WhenDepartmentDoesNotContainsAnyEmployee_ThenThrowException() {
        when(employeeService.showAll()).thenReturn(employees);

        //test & check
        assertThatExceptionOfType(EmployeeNotFoundException.class).
                isThrownBy(() -> departmentService.getMinSalaryByDepartment(999999));
    }

    @Test
    void getMaxSalaryByDepartment_WhenCorrectData_ThenReturnCorrectMinSalary() {
        when(employeeService.showAll()).thenReturn(employees);
        int department = 1;

        Employee expected = employeeService.showAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);

        //test
        Employee actual = departmentService.getMaxSalaryByDepartment(department);

        //check
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getMaxSalaryByDepartment_WhenDepartmentDoesNotContainsAnyEmployee_ThenThrowException() {
        when(employeeService.showAll()).thenReturn(employees);

        //test & check
        assertThatExceptionOfType(EmployeeNotFoundException.class).
                isThrownBy(() -> departmentService.getMaxSalaryByDepartment(999999));
    }

    @Test
    void getAllEmployeesInDepartment_WhenCorrectData_ThenReturnCorrectEmployeeCollections() {
        when(employeeService.showAll()).thenReturn(employees);
        int department = 2;
        List<Employee> expected = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                expected.add(employee);
            }
        }

        //test
        List<Employee> actual = departmentService.getAllEmployeesInDepartment(department);

        //check
        assertThat(expected).containsExactlyInAnyOrderElementsOf(actual);
    }

    @Test
    void getAllEmployeesInDepartment_WhenDepartmentIsNot_ThenReturnEmptyList() {
        when(employeeService.showAll()).thenReturn(employees);

        //test
        List<Employee> expected = departmentService.getAllEmployeesInDepartment(99999);

        //check
        assertThat(expected).isEmpty();
    }

    @Test
    void getAllEmployeesByGroupsDepartments() {
        when(employeeService.showAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expected = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        //test
        Map<Integer, List<Employee>> actual = departmentService.getAllEmployeesByDepartmentGroups();

        //check
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }
}