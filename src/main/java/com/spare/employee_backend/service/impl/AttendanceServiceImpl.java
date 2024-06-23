package com.spare.employee_backend.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.spare.employee_backend.model.Attendance;
import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.service.AttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static final List<Attendance> ATTENDANCE_LIST = new ArrayList<>();
    static {
        try {
            ATTENDANCE_LIST.add(new Attendance("001", "1001", "0", sdf.parse("2024-09-10")));
            ATTENDANCE_LIST.add(new Attendance("002", "1002", "1", sdf.parse("2024-09-10")));
            ATTENDANCE_LIST.add(new Attendance("003", "1003", "2", sdf.parse("2024-09-10")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Attendance> queryAttendances(String employeeId, String status) {
        List<Attendance> res = new ArrayList<>(ATTENDANCE_LIST);
        if (!StringUtil.isNullOrEmpty(employeeId)) {
            res = res.stream().filter(attendance -> attendance.getEmployeeId().equals(employeeId)).collect(Collectors.toList());
        }
        if (!StringUtil.isNullOrEmpty(status)) {
            res = res.stream().filter(attendance -> attendance.getStatus().equals(status)).collect(Collectors.toList());
        }
        return res;
    }

    @Override
    public Response<String> deleteAttendance(String id) {
        if (ATTENDANCE_LIST.stream().noneMatch(attendance -> attendance.getId().equals(id))) {
            return new Response<>(false, "考勤记录" + id + "不存在", null);
        }
        ATTENDANCE_LIST.removeIf(attendance -> attendance.getId().equals(id));
        return new Response<>(true, "考勤记录" + id + "删除成功", null);
    }

    @Override
    public Response<String> createAttendance(Attendance newAttendance) {
        if (newAttendance == null) {
            return new Response<>(false, "请检查输入内容", null);
        }
        newAttendance.setRecordDate(new Date());
        // 自动生成编号
        if (CollectionUtils.isEmpty(ATTENDANCE_LIST)) {
            newAttendance.setId("001");
        } else {
            int maxId = ATTENDANCE_LIST.stream().mapToInt(attendance -> Integer.parseInt(attendance.getId()))
                    .max().orElse(0);
            newAttendance.setId(String.format("%03d", maxId + 1));
        }
        ATTENDANCE_LIST.add(newAttendance);
        return new Response<>(true, "考勤记录" + newAttendance.getId() + "新增成功", null);
    }
}
