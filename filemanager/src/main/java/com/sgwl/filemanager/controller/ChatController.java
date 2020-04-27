//package com.sgwl.filemanager.controller;
//
//import com.sgwl.filemanager.mapper.ChatMapper;
//import com.sgwl.filemanager.po.ChatPo;
//import org.springframework.beans.design.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//public class ChatController {
//
//  @Autowired
//    private ChatMapper chatMapper;
//
//    @RequestMapping("txt")
//    public String getTxt() {
//        List<ChatPo> chatPoList = chatMapper.selectAll();
//        Map<String, List<ChatPo>> map = chatPoList.stream().collect(Collectors.groupingBy(ChatPo::getId));
//        return "";
//    }
//}
