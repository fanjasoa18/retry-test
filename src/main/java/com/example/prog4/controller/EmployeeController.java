package com.example.prog4.controller;

import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.controller.validator.EmployeeValidator;
import com.example.prog4.model.employee.Employee;
import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.service.CSVUtils;
import com.example.prog4.service.EmployeeFacade;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/server/employee")
public class EmployeeController {
    private EmployeeFacade employeeFacade;
    private EmployeeMapper employeeMapper;
    private EmployeeValidator employeeValidator;
    private EmployeeService employeeService;

    @GetMapping("/list/csv")
    public ResponseEntity<byte[]> getCsv(HttpSession session) {
        EmployeeFilter filters = (EmployeeFilter) session.getAttribute("employeeFiltersSession");
        List<Employee> data = employeeService.getAll(filters).stream().map(employeeMapper::toView).toList();

        String csv = CSVUtils.convertToCSV(data);
        byte[] bytes = csv.getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "employees.csv");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @GetMapping("/list/filters/clear")
    public String clearFilters(HttpSession session) {
        session.removeAttribute("employeeFilters");
        return "redirect:/employee/list";
    }

    @PostMapping("/createOrUpdate")
    public String saveOne(@ModelAttribute Employee employee) {
        // Validez l'employé
        employeeValidator.validate(employee);

        // Appelez la méthode de la façade pour enregistrer l'employé
        Employee savedEmployee = employeeMapper.toView(employee);
        employeeFacade.createOrUpdateEmployee(savedEmployee);g

        // Redirigez vers la liste des employés ou effectuez toute autre action nécessaire
        return "redirect:/employee/list";
    }


}
