package com.fwp.study.vo;

import lombok.Data;

import java.util.List;


/**
 * @Description: 华通消息推送的请求参数
 * @Author zhangxubing
 * @Date 2019/11/07 09:59 AM
 * @Version 1.0
 **/
@Data
public class PushMessageRequest {

    /**
     * 推送标题：(必传)
     */
    private String pushTitle;

    /**
     * 推送副标题
     */
    private String pushSubTitle;

    /**
     * 消息推送内容:(必传)
     */
    private String pushContent;

    /**
     * 消息跳转地址
     */
    private String pushUrl;

    /**
     * 是否需要动态传参，默认false:(必传)
     */
    private Boolean pushUrlIsDynamic;

    /**
     * 消息推送平台，默认all,可选:all,ios, android:(必传)
     */
    private String pushPlatform;

    /**
     * 消息推送设备:(必传)
     */
    private String pushAudience;

    /**
     * 消息提示音编码，默认1001:(必传)
     */
    private String pushSound;

    /**
     * 消息来源，最大长度20个字节，一般为消息推送方简写，没有则联系华通开发组:(必传)
     */
    private String pushSource;

    /**
     * 消息接收人集合，当PushAudience值为Unicast时该字段生效，消息接收人的AD账号
     */
    private String pushReciverAdaccount;

    /**
     *
     当PushAudience值为Unicast时该字段生效，消息接收人的手机号码，于AD账号取其一推送，优先取AD列推送,每次最多1000个用户
     */
    private String pushReciverMobileNum;

    /**
     *  TODO
     */
    private String pushReciverTags;

    /**
     *  别名集合
     */
    private String pushReciverAlias;

    /**
     *  记录每个人成功或失败，默认true:(必传)
     */
    private Boolean pushAccelerate;

    /**
     *  消息推送类别编号，没有则联系华通开发组索要对应消息编号:(必传)
     */
    private Boolean msgTypeId;

    /**
     *  消息跳转时页面展示标题:(必传)
     */
    private Boolean pushWebTitle;

    /**
     * 消息列表或详情页展示内容JSON字符串，
     */
    private List<MsgContentJson> pushMsgContentJsonBody;
}
