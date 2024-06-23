package com.spare.employee_backend.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.spare.employee_backend.model.Employee;
import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static final List<Employee> EMPLOYEE_LIST = new ArrayList<>();

    static {
        EMPLOYEE_LIST.add(new Employee("1001", "马云", "0", "13100000001", "董事会"));
        EMPLOYEE_LIST.add(new Employee("1002", "赵云", "0", "13100000002", "人力部"));
        EMPLOYEE_LIST.add(new Employee("1003", "马思纯", "1", "13100000003", "财务部"));
        EMPLOYEE_LIST.add(new Employee("1004", "范彬彬", "1", "13100000004", "会计部"));
        EMPLOYEE_LIST.add(new Employee("1005", "杨过", "0", "13100000005", "外联部"));
        EMPLOYEE_LIST.add(new Employee("1006", "赵露思", "1", "13100000006", "外联部"));
        EMPLOYEE_LIST.add(new Employee("1007", "黄晓明", "0", "13100000007", "安保部"));
    }

    @Override
    public List<Employee> queryEmployees(String id, String name, String gender) {
        List<Employee> res = new ArrayList<>(EMPLOYEE_LIST);
        if (!StringUtil.isNullOrEmpty(id)) {
            res = res.stream().filter(employee -> employee.getId().equals(id)).collect(Collectors.toList());
        }
        if (!StringUtil.isNullOrEmpty(name)) {
            res = res.stream().filter(employee -> employee.getName().equals(name)).collect(Collectors.toList());
        }
        if (!StringUtil.isNullOrEmpty(gender)) {
            res = res.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
        }
        return res;
    }

    @Override
    public Response<String> deleteEmployees(String id) {
        if (EMPLOYEE_LIST.stream().noneMatch(employee -> employee.getId().equals(id))) {
            return new Response<>(false, "工号" + id + "不存在", null);
        }
        int index = 0;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(id)) {
                index = i;
            }
        }
        EMPLOYEE_LIST.remove(index);
        return new Response<>(true, "工号" + id + "删除成功", null);
    }

    @Override
    public Response<String> updateEmployee(Employee newEmployee) {
        EMPLOYEE_LIST.removeIf(employee -> employee.getId().equals(newEmployee.getId()));
        EMPLOYEE_LIST.add(newEmployee);
        return new Response<>(true, "编辑员工" + newEmployee.getId() + "成功", null);
    }

    @Override
    public Response<String> createEmployee(Employee newEmployee) {
        if (newEmployee == null || StringUtil.isNullOrEmpty(newEmployee.getId())) {
            return new Response<>(false, "请检查输入内容", null);
        }
        if (EMPLOYEE_LIST.stream().anyMatch(employee -> employee.getId().equals(newEmployee.getId()))) {
            return new Response<>(false, "工号" + newEmployee.getId() + "已存在", null);
        }
        EMPLOYEE_LIST.add(newEmployee);
        return new Response<>(true, "创建员工" + newEmployee.getId() + "成功", null);
    }
}
