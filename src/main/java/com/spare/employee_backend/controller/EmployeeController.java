package com.spare.employee_backend.controller;

import com.spare.employee_backend.model.Employee;
import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public Response<List<Employee>> queryEmployees(@RequestParam(required = false) String id,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String gender) {
        List<Employee> employeeList = employeeService.queryEmployees(id, name, gender);
        return new Response<>(true, "查询成功", employeeList);
    }

    @DeleteMapping("/delete/{id}")
    public Response<String> deleteEmployees(@PathVariable("id") String id) {
        return employeeService.deleteEmployees(id);
    }
}
