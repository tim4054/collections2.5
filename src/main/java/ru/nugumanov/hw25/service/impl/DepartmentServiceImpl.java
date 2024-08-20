package ru.nugumanov.hw25.service.impl;

import org.springframework.stereotype.Service;
import ru.nugumanov.hw25.exceptions.exceptions.EmployeeNotFoundException;
import ru.nugumanov.hw25.models.Employee;
import ru.nugumanov.hw25.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeService = employeeServiceImpl;
    }

    @Override
    public Employee getMaxSalaryByDepartment(int departmentId) {
        return employeeService.showAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .max((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public Employee getMinSalaryByDepartment(int departmentId) {
        return employeeService.showAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .min((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public double getSalaryByDepartment(int departmentId) {

        List<Employee> employeeList = employeeService.showAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .toList();

        if (employeeList.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employeeList
                .stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public List<Employee> getAllEmployeesInDepartment(Integer departmentId) {
        return employeeService.showAll()
                .stream()
                .filter(x -> x.getDepartment() == departmentId)
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartmentGroups() {
        return employeeService.showAll()
                .stream()
                .collect(Collectors.groupingBy(x -> x.getDepartment()));
    }
}


