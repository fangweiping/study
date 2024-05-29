package com.fwp.demo.service.impl;

import com.fwp.demo.entity.Approval;
import com.fwp.demo.dao.ApprovalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalMapper approvalMapper;

    public Integer insert(Approval approval) {
        approvalMapper.insert(approval);
        return approval.getId();
    }

    public int delete(Integer id) {
        return approvalMapper.delete(id);
    }

    public int update(Approval approval) {
        return approvalMapper.update(approval);
    }

    public Approval query(Integer id) {
        return approvalMapper.query(id);
    }

}
