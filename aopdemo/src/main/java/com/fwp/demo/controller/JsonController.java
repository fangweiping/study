package com.fwp.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fwp.demo.vo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("json")
public class JsonController {

    @RequestMapping("test")
    public String getJsonStr() {
        User user = new User();
        user.setAge(11);
        user.setName("你好");
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(user);
        String json = JSON.toJSONString(objects);
        String a = "[{\"age\":11,\"name\":\"你好\"}]";
        System.out.println(json);
        return a;
    }

    @GetMapping("array")
    public String getArrayJsonString(){
        ArrayList<String> list = new ArrayList<>();
        list.add("renjia");
        list.add("ziji");
        String json = JSON.toJSONString(list);
        System.out.println("json = " + json);
        return json;
    }
}
