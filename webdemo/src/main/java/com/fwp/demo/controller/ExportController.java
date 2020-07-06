package com.fwp.demo.controller;


import com.alibaba.fastjson.JSON;
import com.fwp.demo.vo.OperationResponse;
import com.fwp.demo.vo.OperationTargetValueModel;
import com.fwp.demo.vo.OperationVO;
import com.fwp.demo.vo.SummationsModel;
import lombok.extern.slf4j.Slf4j;
  import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * excel导出
 */
@Slf4j
@RestController
public class ExportController {

    @GetMapping("exportExcel")
    //        localhost:8080/exportExcel
    public void exportExcel(HttpServletResponse response) {

        InputStream is ;
        OutputStream os;
        try {
            // 配置文件下载
            response.setContentType("application/octet-stream");//具体文件类型通过给出的扩展名来确定
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("operationReportsTemplate.xlsx", "utf-8"));//设置文件名

            //1.运作数据
            OperationResponse operationResponse = JSON.parseObject("{\"code\":200,\"success\":true,\"data\":[{\"newUpperShelfSkuNums\":312,\"newUpperShelfNums\":312,\"inStockShelfNums\":21312,\"instoreCurrentNum\":21312,\"inventoryLocationNum\":12312,\"inventorySkuNums\":1231,\"inventoryStockNum\":1231,\"inventoryRate\":0.0578,\"dimensionName\":\"0403仓配一体新建\",\"b2BOperationModel\":{\"b2bDeliveryNum\":142124,\"b2bDeliveryBoxNum\":124124,\"b2bDeliveryPcsNum\":12412412,\"b2bAverBoxNum\":0.8734,\"b2bAverPcsNum\":87.3351,\"b2bDetainByStoreNum\":12412},\"b2COperationModel\":{\"b2cDeliveryNum\":142124,\"b2cDeliveryPackageNum\":124124,\"b2cDeliveryPcsNum\":421,\"b2cAverPackageNum\":0.8734,\"b2cAverPcsNum\":0.003,\"b2cDetainByStoreNum\":1412,\"dayWorkOrderByStoreDealNum\":123,\"dayWorkOrderByDeliveryDealNum\":12,\"shipReachRate\":32.2269},\"operationTargetValueModel\":{\"instoreIntimeRate\":0.0,\"deliveryIntimeRate\":898.0,\"storeComplaintRate\":9.0,\"deliveryComplaintRate\":43.0,\"stockCorrectRate\":780.0,\"detainRate\":5.0,\"stockYield\":null},\"operationCountValueModel\":{\"instoreIntimeRate\":0.001,\"deliveryIntimeRate\":-2.1083,\"storeComplaintRate\":9.0E-4,\"deliveryComplaintRate\":1.0E-4,\"stockCorrectRate\":0.8906,\"detainRate\":0.1,\"stockYield\":-0.1085},\"summationsModel\":{\"newUpperShelfSkuNumsCount\":724,\"newUpperShelfNumsCount\":724,\"inStockShelfNumsCount\":21526,\"instoreCurrentNumCount\":21768,\"inventoryLocationNumCount\":15557,\"inventorySkuNumsCount\":4652,\"inventoryStockNumCount\":13572,\"inventoryRateCount\":0.6235,\"b2bDeliveryNumCount\":143355,\"b2bDeliveryBoxNumCount\":124570,\"b2bDeliveryPcsNumCount\":12416976,\"b2bAverBoxNumCount\":0.869,\"b2bAverPcsNumCount\":86.617,\"b2bDetainByStoreNumCount\":33853,\"b2cDeliveryNumCount\":146778,\"b2cDeliveryPackageNumCount\":124600,\"b2cDeliveryPcsNumCount\":456,\"b2cAverPackageNumCount\":0.8489,\"b2cAverPcsNumCount\":0.0031,\"b2cDetainByStoreNumCount\":1480,\"dayWorkOrderByStoreDealNumCount\":21435,\"dayWorkOrderByDeliveryDealNumCount\":31243,\"shipReachRateCount\":25.2475,\"instoreIntimeRateCount\":0.0023,\"deliveryIntimeRateCount\":-2.1739,\"storeComplaintRateCount\":0.146,\"deliveryComplaintRateCount\":0.2129,\"stockCorrectRateCount\":0.8363,\"detainRateCount\":0.2507,\"stockYieldCount\":-0.3537},\"b2bDeliveryNum\":142124,\"b2bDeliveryBoxNum\":124124,\"b2bDeliveryPcsNum\":12412412,\"b2bAverBoxNum\":null,\"b2bAverPcsNum\":null,\"b2bDetainByStoreNum\":12412,\"b2cDeliveryNum\":142124,\"b2cDeliveryPackageNum\":124124,\"b2cDeliveryPcsNum\":421,\"b2cAverPackageNum\":null,\"b2cAverPcsNum\":null,\"b2cDetainByStoreNum\":1412,\"dayWorkOrderByStoreDealNum\":123,\"dayWorkOrderByDeliveryDealNum\":12,\"shipReachRate\":null,\"instoreIntimeRate\":null,\"deliveryIntimeRate\":null,\"storeComplaintRate\":null,\"deliveryComplaintRate\":null,\"stockCorrectRate\":null,\"detainRate\":null,\"stockYield\":null,\"periodDefectiveNum\":21312,\"physicalDefectiveNum\":2312,\"inventoryLossNum\":null,\"profitLossNum\":2332,\"newCheckReceiveNum\":null,\"newReceivableSkuNum\":null,\"newReceiveSkuNum\":null,\"newReceivableNum\":312123,\"newReceiveNum\":null,\"newUpperShelfNum\":132,\"b2cAcceptNum\":412412,\"b2cCancelNum\":12412,\"b2cReserveNum\":12412,\"b2bBeCompleteNum\":124124,\"b2cBeCompleteNum\":14124,\"b2bDetainByOtherNum\":12412,\"b2bDelayByOtherNum\":124124,\"b2cDetainByOtherNum\":12412,\"b2cDelayByOtherNum\":124124,\"regionCode\":\"sh001\",\"storeCode\":\"sadasd\",\"projectCode\":\"040202004008\"},{\"newUpperShelfSkuNums\":412,\"newUpperShelfNums\":412,\"inStockShelfNums\":214,\"instoreCurrentNum\":456,\"inventoryLocationNum\":3245,\"inventorySkuNums\":3421,\"inventoryStockNum\":12341,\"inventoryRate\":27.0636,\"dimensionName\":\"121\",\"b2BOperationModel\":{\"b2bDeliveryNum\":1231,\"b2bDeliveryBoxNum\":446,\"b2bDeliveryPcsNum\":4564,\"b2bAverBoxNum\":0.3623,\"b2bAverPcsNum\":3.7076,\"b2bDetainByStoreNum\":21441},\"b2COperationModel\":{\"b2cDeliveryNum\":4654,\"b2cDeliveryPackageNum\":476,\"b2cDeliveryPcsNum\":35,\"b2cAverPackageNum\":0.1023,\"b2cAverPcsNum\":0.0075,\"b2cDetainByStoreNum\":68,\"dayWorkOrderByStoreDealNum\":21312,\"dayWorkOrderByDeliveryDealNum\":31231,\"shipReachRate\":0.1599},\"operationTargetValueModel\":{\"instoreIntimeRate\":0.0,\"deliveryIntimeRate\":898.0,\"storeComplaintRate\":9.0,\"deliveryComplaintRate\":43.0,\"stockCorrectRate\":780.0,\"detainRate\":5.0,\"stockYield\":null},\"operationCountValueModel\":{\"instoreIntimeRate\":3.3496,\"deliveryIntimeRate\":4.3304,\"storeComplaintRate\":4.5793,\"deliveryComplaintRate\":6.7106,\"stockCorrectRate\":-1.6996,\"detainRate\":7.9811,\"stockYield\":-11.8136},\"summationsModel\":{\"newUpperShelfSkuNumsCount\":724,\"newUpperShelfNumsCount\":724,\"inStockShelfNumsCount\":21526,\"instoreCurrentNumCount\":21768,\"inventoryLocationNumCount\":15557,\"inventorySkuNumsCount\":4652,\"inventoryStockNumCount\":13572,\"inventoryRateCount\":0.6235,\"b2bDeliveryNumCount\":143355,\"b2bDeliveryBoxNumCount\":124570,\"b2bDeliveryPcsNumCount\":12416976,\"b2bAverBoxNumCount\":0.869,\"b2bAverPcsNumCount\":86.617,\"b2bDetainByStoreNumCount\":33853,\"b2cDeliveryNumCount\":146778,\"b2cDeliveryPackageNumCount\":124600,\"b2cDeliveryPcsNumCount\":456,\"b2cAverPackageNumCount\":0.8489,\"b2cAverPcsNumCount\":0.0031,\"b2cDetainByStoreNumCount\":1480,\"dayWorkOrderByStoreDealNumCount\":21435,\"dayWorkOrderByDeliveryDealNumCount\":31243,\"shipReachRateCount\":25.2475,\"instoreIntimeRateCount\":0.0023,\"deliveryIntimeRateCount\":-2.1739,\"storeComplaintRateCount\":0.146,\"deliveryComplaintRateCount\":0.2129,\"stockCorrectRateCount\":0.8363,\"detainRateCount\":0.2507,\"stockYieldCount\":-0.3537}}],\"msg\":\"操作成功\"}", OperationResponse.class);

            List<OperationVO> operationVOList = operationResponse.getData();

            //获取模板文件
            ClassPathResource resource = new ClassPathResource("operationReportsTemplate.xlsx");
            is = resource.getInputStream();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            XSSFCellStyle titleStyle = sheet.getRow(0).getCell(1).getCellStyle();
            XSSFCellStyle cellStyle = sheet.getRow(2).getCell(1).getCellStyle();

            //1.写入基本数据
            for (int i = 0; i < operationVOList.size(); i++) {
                OperationVO operationVO = operationVOList.get(i);
                List<Object> fieldValueList = parseObject(operationVO);
                for (int j = 0; j < fieldValueList.size(); j++) {
                    Object fieldValue = fieldValueList.get(j);
                    //获取目标单元格
                    XSSFRow row;
                    if (j < 24) {
                        row = sheet.getRow(j );
                    } else {
                        row = sheet.getRow(j + 1);
                    }
                    XSSFCell cell =  row.createCell(i+3);
                    //单元格设置
                    cell.setCellStyle(j==0?titleStyle:cellStyle);
                    cell.setCellValue(fieldValue.toString());
                }
            }

            //2.写入目标值
            OperationTargetValueModel operationTargetValueModel = operationVOList.get(0).getOperationTargetValueModel();
            List<Object> fieldValueList = parseObject(operationTargetValueModel);
            for (int i = 0,j=25; i < fieldValueList.size(); i++,j++) {
                Object fieldValue = fieldValueList.get(i);
                XSSFCell cell = sheet.getRow(j).getCell(2);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(fieldValue.toString());
            }

            //3.写入合计值
            SummationsModel summationsModel = operationVOList.get(0).getSummationsModel();
            fieldValueList = parseObject(summationsModel);
            int columnIndex = 3+operationVOList.size();//合计值列索引
            XSSFCell cell = sheet.getRow(0).createCell(columnIndex);
            cell.setCellValue("合计值");
            cell.setCellStyle(titleStyle);

            for (int i = 0,j=1; i < fieldValueList.size(); i++,j++) {
                Object fieldValue = fieldValueList.get(i);
                //获取目标单元格
                XSSFRow row;
                if (i < 23) {
                    row = sheet.getRow(j);
                } else {
                    row = sheet.getRow(j+1);
                }
                cell = row.createCell(columnIndex);
                //单元格设置
                cell.setCellStyle(cellStyle);
                cell.setCellValue(fieldValue.toString());
            }
            os = response.getOutputStream();
            xssfWorkbook.write(os);
            os.close();
            is.close();
        } catch (Exception e) {
            log.info("数据导出失败", e);
        }
    }

    /**
     * 解析对象获取字段值
     *
     * @param obj
     * @return
     */
    public List<Object> parseObject(Object obj) throws IllegalAccessException {
        List<Object> fieldValueList = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (obj instanceof OperationVO) {
            Field[] newFields=new Field[12];
            System.arraycopy(fields,0,newFields,0,12);
            fields=newFields;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            if(fieldValue==null) continue;
            if (fieldValue instanceof String||fieldValue instanceof Double || fieldValue instanceof Integer) {
                fieldValueList.add(fieldValue);
            } else {
                Class<?> fieldValueClass = fieldValue.getClass();
                for (Field field1 : fieldValueClass.getDeclaredFields()) {
                    field1.setAccessible(true);
                    fieldValueList.add(field1.get(fieldValue));
                    field1.setAccessible(false);
                }
            }
            field.setAccessible(false);
        }
        return fieldValueList;
    }
}
