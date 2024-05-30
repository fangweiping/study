package com.fwp.study.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class POIThreadUtils {

    public static void main(String[] args) throws Exception {

//        singleThreadWrite();


        // 10w数据6s左右,按时间写入6个sheet
        multiThreadWriter();
    }

    public static void multiThreadWriter() throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch doneSignal = new CountDownLatch(6);

        FileOutputStream os = new FileOutputStream("C:\\Users\\OS\\Desktop\\test.xls");

        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet1 = wb.createSheet("1");
        XSSFSheet sheet2 = wb.createSheet("2");
        XSSFSheet sheet3 = wb.createSheet("3");
        XSSFSheet sheet4 = wb.createSheet("4");
        XSSFSheet sheet5 = wb.createSheet("5");
        XSSFSheet sheet6 = wb.createSheet("6");


        //数据准备
        List<List<String>> dataList1 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            List<String> syncList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                syncList.add("你好啊" + i + "    " + j);
            }
            dataList1.add(syncList);
        }
        List<List<String>> dataList2 = JSON.parseObject(JSON.toJSONString(dataList1), new TypeReference<List<List<String>>>() {});
        List<List<String>> dataList3 = JSON.parseObject(JSON.toJSONString(dataList1), new TypeReference<List<List<String>>>() {});
        List<List<String>> dataList4 = JSON.parseObject(JSON.toJSONString(dataList1), new TypeReference<List<List<String>>>() {});
        List<List<String>> dataList5 = JSON.parseObject(JSON.toJSONString(dataList1), new TypeReference<List<List<String>>>() {});
        List<List<String>> dataList6 = JSON.parseObject(JSON.toJSONString(dataList1), new TypeReference<List<List<String>>>() {});

        long start = System.currentTimeMillis();
        executorService.submit(new POIWriter(sheet1, dataList1, doneSignal, 0, 99999));
        executorService.submit(new POIWriter(sheet2, dataList2, doneSignal, 100000, 199999));
        executorService.submit(new POIWriter(sheet3, dataList3, doneSignal, 200000, 299999));
        executorService.submit(new POIWriter(sheet4, dataList4, doneSignal, 300000, 399999));
        executorService.submit(new POIWriter(sheet5, dataList5, doneSignal, 400000, 499999));
        executorService.submit(new POIWriter(sheet6, dataList6, doneSignal, 500000, 599999));

        /**
         * 使用CountDownLatch的await方法，等待所有线程完成sheet操作
         */
        doneSignal.await();

        long flushStart = System.currentTimeMillis();
        wb.write(os);
        os.close();
        executorService.shutdown();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("flush耗时：" + (System.currentTimeMillis() - flushStart));

    }

    static class POIWriter implements Runnable {

        private XSSFSheet sheet;

        private List<List<String>> dataList;

        private CountDownLatch doneSignal;

        private int start;

        private int end;

        public POIWriter(XSSFSheet sheet, List<List<String>> dataList, CountDownLatch doneSignal, int start, int end) {
            this.sheet = sheet;
            this.dataList = dataList;
            this.doneSignal = doneSignal;
            this.start = start;
            this.end = end;
        }

        public List<List<String>> getDataList() {
            return dataList;
        }

        public void setDataList(List<List<String>> dataList) {
            this.dataList = dataList;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public XSSFSheet getSheet() {
            return sheet;
        }

        public void setSheet(XSSFSheet sheet) {
            this.sheet = sheet;
        }

        public CountDownLatch getDoneSignal() {
            return doneSignal;
        }

        public void setDoneSignal(CountDownLatch doneSignal) {
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            write(sheet, start, end, dataList);
            System.out.println(start + "到" + end + "行数据生成！");
            doneSignal.countDown();
        }
    }

    public static void write(XSSFSheet sheet, int start, int end, List<List<String>> dataList) {
        List<List<String>> list = dataList.stream().skip(start).limit(100000).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            List<String> strings = list.get(i);
            for (int j = 0; j < strings.size(); j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(strings.get(j));
            }
        }
    }

    public static void singleThreadWrite() throws Exception {
        long start = System.currentTimeMillis();
        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream os = new FileOutputStream("C:\\Users\\OS\\Desktop\\test.xls");
        XSSFSheet sheet = wb.createSheet();
        for (int i = 0; i < 500000; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue("你好啊");
            }
            System.out.println(i + 1 + "行");
        }
        long flushStart = System.currentTimeMillis();
        wb.write(os);
        os.close();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("flush耗时：" + (System.currentTimeMillis() - flushStart));
    }
}
