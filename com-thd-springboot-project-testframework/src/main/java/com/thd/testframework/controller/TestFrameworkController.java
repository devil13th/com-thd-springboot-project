package com.thd.testframework.controller;

import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.testframework.vo.MyBean01;
import com.thd.testframework.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/testframework")
public class TestFrameworkController extends BasicController {

    /**
     * 测试
     * 1. requestParameter中 Date , Timestamp , LocalDate , LocalDatetime 转换
     * 2. 对象Date , Timestamp , LocalDate , LocalDatetime类型属性的json序列化
     *
     * 配置参见 com.thd.springboot.framework.configuration.DateTimestampConfig
     *
     * @param user
     * @return
     */
    @RequestMapping("/testConverter")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/testframework/testConverter?userBirthday=2020-01-01&ld=2020-01-05&ldt=2020-01-03%2012:13:14&userCreateTime=2020-01-07%2012:13:14
    // url : http://127.0.0.1:8899/thd/testframework/testConverter?userBirthday=2020-01-01&ld=2020-01-05%2012:13:14&ldt=2020-01-03&userCreateTime=2020-01-07
    // url : http://127.0.0.1:8899/thd/testframework/testConverter?userBirthday=1619317065000&ld=1619317065000&ldt=1619317065000&userCreateTime=1619317065000
    public ResponseEntity testConverter(User user){
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

    @RequestMapping("/testPostDate")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/testframework/testPostDate
    public ResponseEntity testPostDate(@RequestBody User user){
        System.out.println(user);
        return ResponseEntity.ok(user);
    }




    @Autowired
    private MyBean01 myBean01;
    @RequestMapping("/test")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/testframework/test
    public ResponseEntity test(){
        return ResponseEntity.ok(myBean01);
    }

}
