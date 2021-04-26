package com.thd.testframework.controller;

import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.testframework.vo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * requestBody中日期类型传值测试
 *
 * 被测试类：
 *      测试jackson反序列化request.body以及序列化日期
 *      com.thd.springboot.framework.web.configuration.WebJackson2ObjectMpperBuilderConfig
 */
@Controller
@RequestMapping("/testJacksonForWeb")
public class TestJackson2ObjectMapperBuilderController extends BasicController {

    @RequestMapping("/testPostDate")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/testJacksonForWeb/testPostDate
    /*
    requestbody:
    {
        "userBirthday":"2021-01-01 12:23:23",
        "ldt":"2021-01-02 12:23:23",
        "ld":"2021-01-03",
        "userCreateTime":"2021|01|04 12:23:23"
    }
     */
    public ResponseEntity testPostDate(@RequestBody User user){
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

}
