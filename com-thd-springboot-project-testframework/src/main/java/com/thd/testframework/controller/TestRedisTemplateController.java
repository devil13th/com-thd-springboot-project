package com.thd.testframework.controller;

import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.testframework.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 测试redisTemplate
 * 被测试类：
 *  com.thd.springboot.framework.redis.configuration.RedisConfig
 */
@Controller
@RequestMapping("/redisTemplate")
public class TestRedisTemplateController extends BasicController {
    @Resource
    private RedisTemplate redisTemplateWithJacksonSer;

    @RequestMapping("/testSaveValue")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redisTemplate/testSaveValue
    public ResponseEntity testSaveValue(){
        User u = User.getInstance();
        this.redisTemplateWithJacksonSer.opsForValue().set("xx",u);
        return ResponseEntity.ok(CommonConstants.STATUS_SUCCESS);
    }
    @RequestMapping("/testGetValue")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redisTemplate/testGetValue
    public ResponseEntity testGetValue(){
        User u = (User)this.redisTemplateWithJacksonSer.opsForValue().get("xx");
        return ResponseEntity.ok(u);
    }
}
