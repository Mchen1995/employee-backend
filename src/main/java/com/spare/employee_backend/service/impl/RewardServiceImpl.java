package com.spare.employee_backend.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.model.Reward;
import com.spare.employee_backend.service.RewardService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final List<Reward> REWARD_LIST = new ArrayList<>();

    static {
        try {
            REWARD_LIST.add(
                    new Reward("001", "1001", "扣工资100元", "迟到", sdf.parse("2024-09-10")));
            REWARD_LIST.add(
                    new Reward("002", "1002", "奖励100元", "提前达标", sdf.parse("2024-09-11")));
            REWARD_LIST.add(
                    new Reward("003", "1002", "扣工资100元", "上班玩手机", sdf.parse("2024-09-12")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reward> queryRewards(String employeeId, String content, String reason) {
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

    @Override
    public Response<String> updateReward(Reward newReward) {
        REWARD_LIST.removeIf(reward -> reward.getId().equals(newReward.getId()));
        newReward.setRecordDate(new Date());
        REWARD_LIST.add(newReward);
        return new Response<>(true, "修改记录成功", null);
    }

    @Override
    public Response<String> deleteRewards(String id) {
        if (REWARD_LIST.stream().noneMatch(reward -> reward.getId().equals(id))) {
            return new Response<>(false, "奖惩记录" + id + "不存在", null);
        }
        int index = 0;
        for (int i = 0; i < REWARD_LIST.size(); i++) {
            if (REWARD_LIST.get(i).getId().equals(id)) {
                index = i;
            }
        }
        REWARD_LIST.remove(index);
        return new Response<>(true, "奖惩记录" + id + "删除成功", null);
    }

    @Override
    public Response<String> createReward(Reward newReward) {
        if (newReward == null) {
            return new Response<>(false, "请检查输入内容", null);
        }
        newReward.setRecordDate(new Date());
        // 自动生成编号
        if (CollectionUtils.isEmpty(REWARD_LIST)) {
            newReward.setId("001");
        } else {
            int maxId = REWARD_LIST.stream().mapToInt(reward -> Integer.parseInt(reward.getId()))
                    .max().orElse(0);
            newReward.setId(String.format("%03d", maxId + 1));
        }
        REWARD_LIST.add(newReward);
        return new Response<>(true, "奖惩记录" + newReward.getId() + "新增成功", null);
    }
}
