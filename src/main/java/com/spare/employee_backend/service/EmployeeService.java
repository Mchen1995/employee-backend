package com.spare.employee_backend.service;

import com.spare.employee_backend.model.Employee;
import com.spare.employee_backend.model.Response;

import java.util.List;

public interface EmployeeService {
    List<Employee> queryEmployees(String id, String name, String gender);

    Response<String> deleteEmployees(String id);

    Response<String> updateEmployee(Employee employee);
    Response<String> createEmployee(Employee employee);
}
