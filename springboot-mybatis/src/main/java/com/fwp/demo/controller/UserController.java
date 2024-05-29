package com.fwp.demo.controller;

import com.fwp.demo.controller.dto.UserLoginRequest;
import com.fwp.demo.entity.User;
import com.fwp.demo.service.UserService;
import com.fwp.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * CREATE TABLE `t_user` (
     *   `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
     *   `username` varchar(50) NOT NULL COMMENT '用户名',
     *   `password` varchar(50) NOT NULL COMMENT '用户密码',
     *   `created_time` timestamp NOT NULL COMMENT '创建时间',
     *   `created_by` varchar(50) NOT NULL COMMENT '创建人',
     *   `updated_time` timestamp NOT NULL COMMENT '修改时间',
     *   `updated_by` varchar(50) NOT NULL COMMENT '修改人',
     *   PRIMARY KEY (`id`),
     *   UNIQUE KEY `uniq_username` (`username`) USING BTREE
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     */

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequest request, HttpServletResponse response) {

        System.out.println(Thread.currentThread().getId());


        User user = userService.queryUserByUserName(request.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            response.sendRedirect("login.jsp");
            return ResponseEntity.status(401).body("用户名或密码错误！");
        }
        //认证成功生成token
        Map<String, String> param = new HashMap<>();
        param.put("id", user.getId().toString());
        param.put("username", user.getUsername());
        String token = JwtUtils.createToken(param, 1000 * 30);
        return ResponseEntity.ok(token);
    }

}
