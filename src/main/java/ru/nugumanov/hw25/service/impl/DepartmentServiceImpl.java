package ru.nugumanov.hw25.service.impl;

import org.springframework.stereotype.Service;
import ru.nugumanov.hw25.models.Employee;
import ru.nugumanov.hw25.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeService = employeeServiceImpl;
    }

    @Override
    public Employee getMaxSalaryByDepartment(int id) {
        return employeeService.showAll()
                .stream()
                .max((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()))
                .orElseThrow();
    }

    @Override
    public Employee getMinSalaryByDepartment(int id) {
        return employeeService.showAll()
                .stream()
                .min((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()))
                .orElseThrow();
    }

    @Override
    public List<Employee> getAllByDepartment(int departmentId) {
        return employeeService.showAll()
                .stream()
                .filter(x -> x.getDepartment() == departmentId)
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> getAllByDepartments() {
        return employeeService.showAll()
                .stream()
                .collect(Collectors.groupingBy(x -> x.getDepartment()));
    }
}

