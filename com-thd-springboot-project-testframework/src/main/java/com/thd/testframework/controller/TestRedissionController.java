package com.thd.testframework.controller;

import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.redis.utils.RedissonUtil;
import com.thd.testframework.vo.User;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 测试RedissionUtil
 * 被测试类：
 *  com.thd.springboot.framework.redis.utils.RedissonUtil
 */
@Controller
@RequestMapping("/redission")
public class TestRedissionController {

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/testtryLock")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redission/testtryLock
    public ResponseEntity testtryLock(){
        System.out.println("=== 开始"  + "|" + Thread.currentThread().getName());
        Lock lock = redissonClient.getLock("lock");
        lock.lock();
        System.out.println("=== 锁住"  + "|" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            System.out.println("=== 已解锁" + "|" + Thread.currentThread().getName());
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(Math.random());
    }

    @RequestMapping("/testunLock")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redission/testunLock
    public ResponseEntity testunLock(){
        RedissonUtil.unLock("mylock");
        System.out.println(Thread.currentThread().getName());
        return ResponseEntity.ok(CommonConstants.STATUS_SUCCESS);
    }
}
