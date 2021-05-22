package com.hand.report.vo;

import lombok.Data;

/**
 * @author chenjiawei
 * @date 2020/7/1 13:59
 * @description:
 */
@Data
public class OperationCountValueModel {

    //统计值

    /**
     * 入库及时率:上架数量之和/应收数量之和
     *
     */
    private Double instoreIntimeRate;

    /**
     * 发货及时率:B2B+B2C发货单量之和/（B2B+B2C应完成单量之和-非仓库原因的4项之和）
     */
    private Double deliveryIntimeRate;

    /**
     * 仓库客诉率:当日已判责工单（仓库原因）之和/ B2C发货单量之和
     */
    private Double storeComplaintRate;

    /**
     * 配送客诉率:当日已判责工单（配送客诉）之和/ B2C发货单量之和
     */
    private Double deliveryComplaintRate;

    /**
     * 库存准确率:1-盈亏绝对值之和/a之和
     */
    private Double stockCorrectRate;

    /**
     * 扣单率:扣单率=每日仓库原因扣单2项之和的和/（B2B+B2C应完成单量之和）
     */
    private Double detainRate;

    /**
     * 库存良品率:（a之和-2个残次数量和的的之和）/a之和
     */
    private Double stockYield;


}
