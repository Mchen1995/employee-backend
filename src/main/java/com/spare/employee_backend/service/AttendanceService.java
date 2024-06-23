package com.spare.employee_backend.service;

import com.spare.employee_backend.model.Attendance;
import com.spare.employee_backend.model.Response;

import java.util.List;

public interface AttendanceService {
    List<Attendance> queryAttendances(String employeeId, String status);

    Response<String> deleteAttendance(String id);

    Response<String> createAttendance(Attendance attendance);
}
