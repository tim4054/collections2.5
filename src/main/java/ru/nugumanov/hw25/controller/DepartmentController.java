package ru.nugumanov.hw25.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/max-salary")
    public Employee getMaxSalaryByDepartment(@RequestParam("department") int departmentId) {
        return departmentService.getMaxSalaryByDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalaryByDepartment(@RequestParam("department") int departmentId) {
        return departmentService.getMinSalaryByDepartment(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> getAllByDepartment(@RequestParam("department") int departmentId) {
        return departmentService.getAllByDepartment(departmentId);
    }

    @GetMapping("/all-by-dep")
    public Map<Integer, List<Employee>> getAllByDepartments() {
        return departmentService.getAllByDepartments();
    }
}
