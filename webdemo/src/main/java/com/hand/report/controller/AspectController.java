package com.hand.report.controller;/**
 * @author Fang WeiPing
 * @date 2020/6/1 10:44
 */

import com.hand.report.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/6/1 10:44
 */
@RestController
@RequestMapping("aspect")
public  class AspectController {

    @RequestMapping("test")
    public User testAspect() {
//        int i = 1/0;
        return new User("1", 22);
    }
}
