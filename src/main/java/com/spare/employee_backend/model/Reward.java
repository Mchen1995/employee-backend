package com.spare.employee_backend.model;

import java.util.Date;

/**
 * 奖惩模块
 */
public class Reward {
    /**
     * id
     */
    private String id;

    /**
     * 员工 ID
     */
    private String employeeId;
    /**
     * 内容
     */
    private String content;

    /**
     * 原因
     */
    private String reason;

    /**
     * 日期
     */
    private Date recordDate;

    public Reward(String id, String employeeId, String content, String reason, Date recordDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.content = content;
        this.reason = reason;
        this.recordDate = recordDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
