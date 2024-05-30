package com.fwp.study.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 消息列表详情内容
 * @Author zhangxubing
 * @Date 2019/11/07 10:27 Am
 * @Version 1.0
 **/
@Data
public class MsgContentJson {

    private String msgContent;
    private String msgDetailUrl;
    private String msgImage;
    private String msgFormat;
    private String msgOpenTitle;
    private String msgDownSystemId;
    private List<SubMsg> msgSubContent;
}
