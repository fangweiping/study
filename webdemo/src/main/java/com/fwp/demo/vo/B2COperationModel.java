package com.fwp.demo.vo;

import lombok.Data;

/**
 * @author chenjiawei
 * @date 2020/7/1 13:45
 * @description:
 */
@Data
public class B2COperationModel {

    /**
     * 发货单量:
     */
    private Integer b2cDeliveryNum;
    /**
     * 发货包裹数
     */
    private Integer b2cDeliveryPackageNum;
    /**
     * 发货件数
     */
    private Integer b2cDeliveryPcsNum;
    /**
     * 单均包裹数:发货箱数/发货件数
     */
    private Double b2cAverPackageNum;
    /**
     * 单均件数:发货单量/发货件数
     */
    private Double b2cAverPcsNum;
    /**
     * 扣单数:B2C扣单数（仓库原因）求和
     */
    private Integer b2cDetainByStoreNum;

    /**
     * 当日已判责工单（仓库原因）
     */
    private Integer dayWorkOrderByStoreDealNum;
    /**
     * 当日已判责工单（配送原因）
     */
    private Integer dayWorkOrderByDeliveryDealNum;
    /**
     * 单量达成率：=B2C （接单单量之和-取消单量之和）/预测单量之和
     */
    private Double shipReachRate;
}
