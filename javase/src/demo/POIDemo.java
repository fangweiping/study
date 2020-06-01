package demo;/**
 * @author Fang WeiPing
 * @date 2020/5/27 19:05
 */

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/27 19:05
 */
@SuppressWarnings("all")
public class POIDemo {
    public static void main(String[] args) {
        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(new FileInputStream("C:\\Users\\Admin\\Desktop\\1.xls"));

            HSSFWorkbook wb = new HSSFWorkbook(fs);

            Map<String, Integer> skuNoNumMap = new HashMap<>();

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                HSSFSheet sheetAt = wb.getSheetAt(i);
               a: for (int j = 0; j < sheetAt.getPhysicalNumberOfRows(); j++) {
                    HSSFRow row = sheetAt.getRow(j);
                    if (row == null) {
                        break a;
                    }

                    //将文本转成普通数值类型,获取以后以字符串形式导出
                    String skuNo = new BigDecimal(row.getCell(0).getNumericCellValue()).toString().trim();
                    System.out.println("skuNo = " + skuNo);
                    BigDecimal.valueOf(Double.valueOf(skuNo));//判断是否未正确数值
                    Integer num = new Double(row.getCell(1).getNumericCellValue()).intValue();

                    if (!skuNoNumMap.containsKey(skuNo)) {
                        skuNoNumMap.put(skuNo, num);
                    } else {
                        skuNoNumMap.put(skuNo, num + skuNoNumMap.get(skuNo));
                    }
                   System.out.println("j = " + (j+1));
                }
            }

            //以字符串写入
            HSSFWorkbook excel= new HSSFWorkbook();

            HSSFSheet sheet = excel.createSheet();

            int i = 0;
            for (String skuNo : skuNoNumMap.keySet()) {
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(skuNo);
                row.createCell(1).setCellValue(skuNoNumMap.get(skuNo).toString());
                i++;
            }

           FileOutputStream out =new FileOutputStream("C:\\Users\\Admin\\Desktop\\导出.xls");
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

    public String getValue(HSSFCell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cell.getNumericCellValue();
                break;
            case Cell.CELL_TYPE_STRING:
                cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_ERROR:
                cell.getErrorCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                cell.getNumericCellValue();
                break;
        }

        return null;
    }

}
