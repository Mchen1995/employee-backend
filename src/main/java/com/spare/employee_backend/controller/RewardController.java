package com.spare.employee_backend.controller;

import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.model.Reward;
import com.spare.employee_backend.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @GetMapping("/list")
    public Response<List<Reward>> queryRewards(@RequestParam(required = false) String employeeId,
                                               @RequestParam(required = false) String content,
                                               @RequestParam(required = false) String reason) {
        List<Reward> rewardList = rewardService.queryRewards(employeeId, content, reason);
        return new Response<>(true, "查询成功", rewardList);
    }

    @PutMapping("/update")
    public Response<String> updateReward(@RequestBody Reward reward) {
        return rewardService.updateReward(reward);
    }

    @DeleteMapping("/delete/{id}")
    public Response<String> deleteRewards(@PathVariable("id") String id) {
        return rewardService.deleteRewards(id);
    }

    @PostMapping("/create")
    public Response<String> createEmployee(@RequestBody Reward reward) {
        return rewardService.createReward(reward);
    }
}
