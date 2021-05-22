package com.hand.report.vo;

import lombok.Data;

/**
 * @author chenjiawei
 * @date 2020/7/3 18:32
 * @description:
 */
@Data
public class SummationsModel {
    /**
     * 上架SKU:
     */
    private Integer newUpperShelfSkuNumsCount;
    /**
     * 上架数量:
     */
    private Integer newUpperShelfNumsCount;

    /**
     * 在库SKU:
     */
    private Integer inStockShelfNumsCount;

    /**
     * 期初数量+入库数量:
     */
    private Integer instoreCurrentNumCount;
    /**
     * 盘点库位
     */
    private Integer inventoryLocationNumCount;
    /**
     * 盘点SKU
     */
    private Integer inventorySkuNumsCount;
    /**
     * 盘点件数(库存)
     */
    private Integer inventoryStockNumCount;
    /**
     * 盘点比例
     */
    private Double inventoryRateCount;

    /**
     * 发货单量:求和
     */
    private Integer b2bDeliveryNumCount;

    /**
     * 发货箱数:求和
     */
    private Integer b2bDeliveryBoxNumCount;

    /**
     * 发货件数:求和
     */
    private Integer b2bDeliveryPcsNumCount;
    /**
     * 单均箱数:发货箱数/发货件数
     */
    private Double b2bAverBoxNumCount;
    /**
     * 单均件数:发货单量/发货件数
     */
    private Double b2bAverPcsNumCount;

    /**
     * 扣单数:B2B扣单数（仓库原因）求和
     */
    private Integer b2bDetainByStoreNumCount;

    /**
     * 发货单量:
     */
    private Integer b2cDeliveryNumCount;
    /**
     * 发货包裹数
     */
    private Integer b2cDeliveryPackageNumCount;
    /**
     * 发货件数
     */
    private Integer b2cDeliveryPcsNumCount;
    /**
     * 单均包裹数:发货箱数/发货件数
     */
    private Double b2cAverPackageNumCount;
    /**
     * 单均件数:发货单量/发货件数
     */
    private Double b2cAverPcsNumCount;
    /**
     * 扣单数:B2C扣单数（仓库原因）求和
     */
    private Integer b2cDetainByStoreNumCount;
    /**
     * 当日已判责工单（仓库原因）
     */
    private Integer dayWorkOrderByStoreDealNumCount;
    /**
     * 当日已判责工单（配送原因）
     */
    private Integer dayWorkOrderByDeliveryDealNumCount;
    /**
     * 单量达成率：=B2C （接单单量之和-取消单量之和）/预测单量之和
     */
    private Double shipReachRateCount;

    //统计值

    /**
     * 入库及时率:上架数量之和/应收数量之和
     *
     */
    private Double instoreIntimeRateCount;

    /**
     * 发货及时率:B2B+B2C发货单量之和/（B2B+B2C应完成单量之和-非仓库原因的4项之和）
     */
    private Double deliveryIntimeRateCount;

    /**
     * 仓库客诉率:当日已判责工单（仓库原因）之和/ B2C发货单量之和
     */
    private Double storeComplaintRateCount;

    /**
     * 配送客诉率:当日已判责工单（配送客诉）之和/ B2C发货单量之和
     */
    private Double deliveryComplaintRateCount;

    /**
     * 库存准确率:1-盈亏绝对值之和/a之和
     */
    private Double stockCorrectRateCount;

    /**
     * 扣单率:扣单率=每日仓库原因扣单2项之和的和/（B2B+B2C应完成单量之和）
     */
    private Double detainRateCount;

    /**
     * 库存良品率:（a之和-2个残次数量和的的之和）/a之和
     */
    private Double stockYieldCount;

}
