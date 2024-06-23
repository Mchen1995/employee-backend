package com.spare.employee_backend.controller;

import com.spare.employee_backend.model.Attendance;
import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/list")
    public Response<List<Attendance>> queryEmployees(@RequestParam(required = false) String employeeId,
                                                   @RequestParam(required = false) String status) {
        List<Attendance> attendanceList = attendanceService.queryAttendances(employeeId, status);
        return new Response<>(true, "查询成功", attendanceList);
    }


    @PostMapping("/create")
    public Response<String> createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.createAttendance(attendance);
    }

    @DeleteMapping("/delete/{id}")
    public Response<String> deleteAttendance(@PathVariable("id") String id) {
        return attendanceService.deleteAttendance(id);
    }
}
