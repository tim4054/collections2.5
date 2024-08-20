package ru.nugumanov.hw25.controller;

import org.springframework.web.bind.annotation.*;
import ru.nugumanov.hw25.models.Employee;
import ru.nugumanov.hw25.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/max")
    public Employee getMaxSalaryByDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.getMaxSalaryByDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getMinSalaryByDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.getMinSalaryByDepartment(departmentId);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesInDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.getAllEmployeesInDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public double getSalaryByDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.getSalaryByDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesByGroupsDepartments() {
        return departmentService.getAllEmployeesByDepartmentGroups();
    }
}
