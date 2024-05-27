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

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequest request, HttpServletResponse response) {
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
