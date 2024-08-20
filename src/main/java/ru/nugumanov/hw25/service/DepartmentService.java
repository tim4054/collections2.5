package ru.nugumanov.hw25.service;

import ru.nugumanov.hw25.models.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getMaxSalaryByDepartment(int departmentId);

    Employee getMinSalaryByDepartment(int departmentId);

    double getSalaryByDepartment(int departmentId);

    List<Employee> getAllEmployeesInDepartment(Integer departmentId);

    Map<Integer, List<Employee>> getAllEmployeesByDepartmentGroups();
}
