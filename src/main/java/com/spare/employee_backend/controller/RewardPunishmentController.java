package com.spare.employee_backend.controller;

import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.model.Reward;
import com.spare.employee_backend.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reward")
public class RewardPunishmentController {
    @Autowired
    private RewardService rewardService;

    @GetMapping("/list")
    public Response<List<Reward>> queryRewardPunishment(@RequestParam(required = false) String employeeId,
                                                        @RequestParam(required = false) String content,
                                                        @RequestParam(required = false) String reason) {
        List<Reward> rewardList = rewardService.queryRewardPunishment(employeeId, content, reason);
        return new Response<>(true, "查询成功", rewardList);
    }
}
