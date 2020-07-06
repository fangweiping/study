package com.fwp.demo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjiawei
 * @date 2020/7/1 9:56
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationVO  implements Serializable {
    /**
     * 展示维度名称
     */
    @ApiModelProperty(value = "展示维度名称")
    private String dimensionName;

    //运作数据
    /**
     * 上架SKU:查询周期内的每日 包含项目的上架SKU之和
     */
    @ApiModelProperty(value = "上架SKU")
    private Integer newUpperShelfSkuNums;
    /**
     * 上架数量:查询周期内的每日 上架数量之和
     */
    @ApiModelProperty(value = "上架数量")
    private Integer newUpperShelfNums;

    /**
     * 在库SKU:截止日期的包含项目的在库SKU之和
     */
    @ApiModelProperty(value = "在库SKU")
    private Integer inStockShelfNums;

    /**
     * 期初数量+入库数量:截止日期的包含项目的 期初数量+入库数量
     */
    @ApiModelProperty(value = "期初数量+入库数量")
    private Integer instoreCurrentNum;
    /**
     * 盘点库位
     */
    @ApiModelProperty(value = "盘点库位")
    private Integer inventoryLocationNum;
    /**
     * 盘点SKU
     */
    @ApiModelProperty(value = "盘点SKU")
    private Integer inventorySkuNums;
    /**
     * 盘点件数(库存)
     */
    @ApiModelProperty(value = "盘点件数(库存)")
    private Integer inventoryStockNum;
    /**
     * 盘点比例
     */
    @ApiModelProperty(value = "盘点比例")
    private Double inventoryRate;

    /**
     * B2B
     */
    @ApiModelProperty(value = "运作数据：b2b")
    private B2BOperationModel b2BOperationModel;

    /**
     * B2C
     */
    @ApiModelProperty(value = "运作数据：b2c")
    private B2COperationModel b2COperationModel;
    //服务质量

    /**
     * 统计值
     */
    @ApiModelProperty(value = "服务质量：统计值")
    private OperationCountValueModel operationCountValueModel;

    /**
     * 目标值
     */
    @ApiModelProperty(value = "服务质量：目标值")
    private OperationTargetValueModel operationTargetValueModel;

    /**
     * 数据合计
     */
    @ApiModelProperty(value = "服务质量：统计值")
    private SummationsModel summationsModel;
    /**
     * 发货单量:求和
     */
    private Integer b2bDeliveryNum;

    /**
     * 发货箱数:求和
     */
    private Integer b2bDeliveryBoxNum;

    /**
     * 发货件数:求和
     */
    private Integer b2bDeliveryPcsNum;
    /**
     * 单均箱数:发货箱数/发货件数
     */
    private Double b2bAverBoxNum;
    /**
     * 单均件数:发货单量/发货件数
     */
    private Double b2bAverPcsNum;

    /**
     * 扣单数:B2B扣单数（仓库原因）求和
     */
    private Integer b2bDetainByStoreNum;

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

    /**
     * 效期残次数量
     */
    private Integer periodDefectiveNum;

    /**
     * 实物残次数量
     */
    private Integer physicalDefectiveNum;




    /**
     * 盘亏数量
     */
    private Integer inventoryLossNum;

    /**
     * 盈亏绝对值
     */
    private Integer profitLossNum;

    /**
     * 考核收货数量
     */
    private Integer newCheckReceiveNum;


    /**
     * 应收SKU
     */
    private Integer newReceivableSkuNum;

    /**
     * 收货SKU
     */
    private Integer newReceiveSkuNum;


    /**
     * 应收数量
     */
    private Integer newReceivableNum;

    /**
     * 收货数量
     */
    private Integer newReceiveNum;

    /**
     * 上架数量
     */
    private Integer newUpperShelfNum;
    /**
     * b2c接单量
     */
    private Integer b2cAcceptNum;
    /**
     * b2c外部取消单量
     */
    private Integer b2cCancelNum;
    /**
     * 预测单量
     */
    private Integer b2cReserveNum;

    /**
     * b2b应完成单量
     */
    private Integer b2bBeCompleteNum;

    /**
     * b2c应完成单量
     */
    private Integer b2cBeCompleteNum;

    /**
     * b2b扣单（非仓库原因）
     */
    private Integer b2bDetainByOtherNum;

    /**
     * b2b延迟订单（非仓库原因）
     */
    private Integer b2bDelayByOtherNum;
    /**
     * b2c扣单（非仓库原因）
     */
    private Integer b2cDetainByOtherNum;

    /**
     * b2c延迟订单（非仓库原因）
     */
    private Integer b2cDelayByOtherNum;
    /**
     * 大区编码
     */

    @ApiModelProperty(value = "大区编码")
    private String regionCode;

    /**
     * 仓库编码
     */

    @ApiModelProperty(value = "仓库编码")
    private String storeCode;

    /**
     * 项目编码 唯一标识使用projectCode
     */

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

}
