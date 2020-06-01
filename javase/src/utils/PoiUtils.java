package utils;/**
 * @author Fang WeiPing
 * @date 2020/5/29 11:57
 */

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/29 11:57 
 */
@SuppressWarnings("all")
public class PoiUtils {

    /**
     * 对于excel中不同的数据类型,统一转成文本类型,方便在代码中进行处理
     * 对于直接传进来的,通过代码判断cell类型就行取值
     */
    @Test
    public void test() {
        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(new FileInputStream("C://Users//EDZ//Desktop//汇总.xls"));

            HSSFWorkbook wb = new HSSFWorkbook(fs);

            Map<String, Integer> skuNoNumMap = new HashMap<>();

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                HSSFSheet sheetAt = wb.getSheetAt(i);
                a: for (int j = 0; j < sheetAt.getPhysicalNumberOfRows(); j++) {
                    HSSFRow row = sheetAt.getRow(j);
                    if (row == null) {
                        break a;
                    }
//                    String skuNo = new BigDecimal(row.getCell(0).getNumericCellValue()).toString();
//                    Integer num = new Double(row.getCell(1).getNumericCellValue()).intValue();

                    String skuNo = row.getCell(0).getStringCellValue();
                    Integer num = new Integer(row.getCell(1).getStringCellValue());

                    if (!skuNoNumMap.containsKey(skuNo)) {
                        skuNoNumMap.put(skuNo, num);
                    } else {
                        skuNoNumMap.put(skuNo, num + skuNoNumMap.get(skuNo));
                    }
                    System.out.println("j = " + (j+1));
                }
            }

            //写入
            HSSFWorkbook excel= new HSSFWorkbook();

            HSSFSheet sheet = excel.createSheet();

            int i = 0;
            for (String skuNo : skuNoNumMap.keySet()) {
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(skuNo);
                row.createCell(1).setCellValue(skuNoNumMap.get(skuNo).toString());
                i++;
            }

            FileOutputStream out =new FileOutputStream("C://Users//EDZ//Desktop//入库单.xls");
            excel.write(out);

            System.out.println("skuNoNumMap = " + skuNoNumMap);
            System.out.println("skuNoNumMap = " + skuNoNumMap.size());
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
