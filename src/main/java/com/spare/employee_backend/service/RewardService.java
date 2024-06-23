package com.spare.employee_backend.service;

import com.spare.employee_backend.model.Reward;

import java.util.List;

public interface RewardService {
    List<Reward> queryRewardPunishment(String employeeId, String content, String reason);
}
