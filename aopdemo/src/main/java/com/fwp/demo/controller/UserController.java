package com.fwp.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fwp.demo.aop.annotation.Login;
import com.fwp.demo.vo.RespResult;
import org.omg.IOP.Encoding;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("login")
    @Login("开始登陆")
    public String doLogin() {
        RespResult resp = new RespResult();
        resp.setCode(200);
        resp.setMsg("登陆成功");
        resp.setData(null);
        return JSON.toJSONString(resp);
    }



    @GetMapping("file")
    public String getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取资源路径
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("files\\1.txt");
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("1.txt", "UTF-8"));

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

    @PostMapping("upload")
    public String uploadFile(@RequestParam("file")MultipartFile file,HttpServletRequest request) throws IOException {
        byte[] bytes = file.getBytes();

        InputStreamReader isr = new InputStreamReader(file.getInputStream(),"utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(new File("d:\\" + file.getOriginalFilename())),"utf-8");
        char[] chars = new char[1024] ;
        while (isr.read(chars)!=-1){
            osw.write(chars);
            osw.flush();
        }
        osw.close();
        isr.close();
        return null;
    }
}
