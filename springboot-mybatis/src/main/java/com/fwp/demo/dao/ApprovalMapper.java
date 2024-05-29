package com.fwp.demo.dao;

import com.fwp.demo.entity.Approval;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApprovalMapper {

    int insert(Approval approval);

    int update(Approval approval);

    Approval query(Integer id);

    int delete(Integer id);
}
