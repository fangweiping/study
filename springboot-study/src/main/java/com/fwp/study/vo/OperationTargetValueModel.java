package com.fwp.study.vo;

import lombok.Data;

/**
 * @author chenjiawei
 * @date 2020/7/1 13:47
 * @description:
 */
@Data
public class OperationTargetValueModel {

    /**
     * 入库及时率
     */
    private Double instoreIntimeRate;

    /**
     * 发货及时率
     */
    private Double deliveryIntimeRate;

    /**
     * 仓库客诉率
     */
    private Double storeComplaintRate;

    /**
     * 配送客诉率
     */
    private Double deliveryComplaintRate;

    /**
     * 库存准确率
     */
    private Double stockCorrectRate;

    /**
     * 扣单率
     */
    private Double detainRate;
    /**
     * 库存良品率:（a之和-2个残次数量和的的之和）/a之和
     */
    private Double stockYield;

}
