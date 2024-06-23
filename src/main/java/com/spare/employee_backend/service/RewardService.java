package com.spare.employee_backend.service;

import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.model.Reward;

import java.util.List;

public interface RewardService {
    List<Reward> queryRewards(String employeeId, String content, String reason);

    Response<String> updateReward(Reward reward);

    Response<String> deleteRewards(String id);

    Response<String> createReward(Reward reward);
}
