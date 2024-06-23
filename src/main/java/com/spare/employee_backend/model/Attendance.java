package com.spare.employee_backend.model;

import java.util.Date;

/**
 * 考勤记录
 */
public class Attendance {
    /**
     * 记录编号
     */
    private String id;

    /**
     * 员工编号
     */
    private String employeeId;

    /**
     * 状态（0-正常；1-迟到；2-未打卡）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date recordDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Attendance(String id, String employeeId, String status, Date recordDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.status = status;
        this.recordDate = recordDate;
    }
}
