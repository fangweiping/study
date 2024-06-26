package com.fwp.study.controller;

import com.alibaba.fastjson.JSON;
import com.fwp.study.vo.RespResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileController {

    @CrossOrigin("https://www.cnblogs.com")
    @GetMapping("resource")
    public String getResourceFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取资源路径
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("1.csv");
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();

        // 配置文件下载
        response.setContentType("application/octet-stream");//具体文件类型通过给出的扩展名来确定
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("1.csv", "utf-8"));//设置文件名

        //缓冲数组
        byte[] buf = new byte[1024];
        while (resourceAsStream.read(buf) != -1) {
            outputStream.write(buf);
        }
        //关闭资源
        outputStream.close();
        resourceAsStream.close();

        RespResult resp = new RespResult();
        resp.setCode(200);
        resp.setMsg("登陆成功");
        resp.setData(null);
        return JSON.toJSONString(resp);
    }


    /**
     * 文件上传
     * @param file
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String charset = null;
        //基本上文件上传除了 gbk 就是utf-8    ,gbk 就是ansi
        byte[] b = file.getBytes();
        if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
            charset = "utf-8";
        } else {
            charset = "gbk";
        }
        InputStreamReader isr = new InputStreamReader(file.getInputStream(), charset);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(new File("d:\\" + file.getOriginalFilename())), "utf-8");
        char[] chars = new char[1024];
        while (isr.read(chars) != -1) {
            osw.write(chars);
            osw.flush();
        }
        osw.close();
        isr.close();
        return null;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("download")
    public void getU8File(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 配置文件下载
        response.setContentType("application/octet-stream");//具体文件类型通过给出的扩展名来确定
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("1.csv", "utf-8"));//设置文件名
        OutputStream os = response.getOutputStream();

        //该list保存每一行聊天数据
        List<String> content = new ArrayList<>();
        content.add("你好");
        content.add("我好");
        OutputStreamWriter osw = new OutputStreamWriter(os, "gbk");
        for (String s : content) {
            osw.write(s);
            osw.write(System.lineSeparator());
            osw.flush();
        }
        //关闭资源
        //.....
    }
}
