package com.fwp.demo.controller;

import com.fwp.demo.entity.Approval;
import com.fwp.demo.service.impl.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @PostMapping("/insert")
    public Integer insert(@RequestBody Approval approval) {
        //返回主键id
        return approvalService.insert(approval);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        int rows = approvalService.delete(id);
        if (rows > 0) {
            return "删除成功";
        }
        return "删除失败";
    }

    @PostMapping("/update")
    public String update(@RequestBody Approval approval) {
        int rows = approvalService.update(approval);
        if (rows > 0) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("/query/{id}")
    public Approval query(@PathVariable Integer id) {
        return approvalService.query(id);
    }

    /**
     *
     CREATE TABLE `t_approval` (
     `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `content` varchar(500) NOT NULL COMMENT '审批内容',
     `status` tinyint NOT NULL COMMENT '审批状态  0：保存；1：审批中 ；2：审批通过；3：审批不通过；4：审核驳回；5：审批结束';
     `d_flag` bit(1) NOT NULL COMMENT '删除标识  0：未删除 1：已删除';
     PRIMARY KEY (`id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     *
     */

}
