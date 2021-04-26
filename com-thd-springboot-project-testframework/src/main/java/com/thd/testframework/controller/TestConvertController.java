package com.thd.testframework.controller;

import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.testframework.vo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * request参数中日期类型传值测试
 *
 * 被测试类：
 *      测试request参数(日期类型)的转换
 *      com.thd.springboot.framework.web.configuration.DateTimestampConfig
 *
 */
@Controller
@RequestMapping("/testConvert")
public class TestConvertController extends BasicController {

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
    @RequestMapping("/testConvert")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/testConvert/testConvert?userBirthday=2020-01-01&ld=2020-01-05&ldt=2020-01-03%2012:13:14&userCreateTime=2020-01-07%2012:13:14
    // url : http://127.0.0.1:8899/thd/testConvert/testConvert?userBirthday=2020-01-01&ld=2020-01-05%2012:13:14&ldt=2020-01-03&userCreateTime=2020-01-07
    // url : http://127.0.0.1:8899/thd/testConvert/testConvert?userBirthday=1619317065000&ld=1619317065000&ldt=1619317065000&userCreateTime=1619317065000
    public ResponseEntity testConvert(User user){
        System.out.println(user);
        return ResponseEntity.ok(user);
    }



}
