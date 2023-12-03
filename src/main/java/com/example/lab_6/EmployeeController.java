package com.example.lab_6;

// EmployeeController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String showEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "main"; // Название HTML-шаблона для главной страницы
    }

    @GetMapping("/new")
    public String showAddEmployeeForm() {
        return "addEmployee"; // Название HTML-шаблона для страницы добавления сотрудника
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/"; // Перенаправление на главную страницу после добавления сотрудника
    }


}
