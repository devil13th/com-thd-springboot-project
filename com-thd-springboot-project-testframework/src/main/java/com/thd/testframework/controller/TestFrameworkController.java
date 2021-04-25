package com.thd.testframework.controller;

import com.thd.springboot.framework.web.BasicController;
import com.thd.testframework.vo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/testframework")
public class TestFrameworkController extends BasicController {
    @RequestMapping("/testDate")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/testframework/testDate?userBirthday=2020-01-01
    public ResponseEntity testGetDate(User user){
        System.out.println(user);
        return ResponseEntity.ok("SUCCESS");
    }

    @RequestMapping("/testPostDate")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/testframework/testPostDate
    public ResponseEntity testPostDate(@RequestBody User user){
        System.out.println(user);
        return ResponseEntity.ok(user);
    }
}
