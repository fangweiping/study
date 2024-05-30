package com.fwp.demo.vo;

import lombok.Data;

/**
 * @author chenjiawei
 * @date 2020/7/1 13:45
 * @description:
 */
@Data
public class B2BOperationModel {

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


}
