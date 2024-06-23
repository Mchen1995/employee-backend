package com.spare.employee_backend.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.spare.employee_backend.model.Reward;
import com.spare.employee_backend.service.RewardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {
    public static final List<Reward> REWARD_LIST = new ArrayList<>();
    static {
        REWARD_LIST.add(
                new Reward("001", "1001", "扣工资100元", "迟到", new Date(2024, 8, 10)));
        REWARD_LIST.add(
                new Reward("002","1002", "奖励100元", "提前达标", new Date(2024, 8, 11)));
        REWARD_LIST.add(
                new Reward("003","1002", "扣工资100元", "上班玩手机", new Date(2024, 8, 11)));
    }
    @Override
    public List<Reward> queryRewardPunishment(String employeeId, String content, String reason) {
        List<Reward> res = new ArrayList<>(REWARD_LIST);
        if (!StringUtil.isNullOrEmpty(employeeId)) {
            res = res.stream().filter(reward -> reward.getEmployeeId().equals(employeeId)).collect(Collectors.toList());
        }
        if (!StringUtil.isNullOrEmpty(content)) {
            res = res.stream().filter(reward -> reward.getContent().equals(content)).collect(Collectors.toList());
        }
        if (!StringUtil.isNullOrEmpty(reason)) {
            res = res.stream().filter(reward -> reward.getReason().equals(reason)).collect(Collectors.toList());
        }
        return res;
    }
}
