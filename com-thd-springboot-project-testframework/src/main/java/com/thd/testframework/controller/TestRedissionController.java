package com.thd.testframework.controller;

import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.redis.utils.RedissonUtil;
import com.thd.testframework.vo.User;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping("/testLock")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redission/testLock
    public ResponseEntity testLock(){
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



    @RequestMapping("/testSetValue")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redission/testSetValue
    public ResponseEntity testSetValue(){
        User u = User.getInstance();
        this.redissonClient.getBucket("r").set(u);
        return ResponseEntity.ok(Math.random());
    }

    @RequestMapping("/testGetValue")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redission/testGetValue
    public ResponseEntity testGetValue(){
        User u = (User)this.redissonClient.getBucket("r").get();

        return ResponseEntity.ok(u);
    }

    @RequestMapping("/testtryLock")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redission/testtryLock
    public ResponseEntity testtryLock(){
        System.out.println("=== 开始"  + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
        RLock lock = redissonClient.getLock("lock");

        try {
            // 锁等待时间：5秒   获取锁1秒后自动解锁
            if(lock.tryLock(5,1,TimeUnit.SECONDS)) {
                System.out.println("=== 锁住" + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
                Thread.sleep(3000);
            }else{
                System.out.println("=== 没有获取到锁" + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try{
                lock.unlock();
                System.out.println("=== 解锁" + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
            }catch (Exception e){
                System.out.println("=== 未能解锁" + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
            }


        }

        return ResponseEntity.ok(Math.random());
    }



    @RequestMapping("/testtryLock2")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/redission/testtryLock2
    public ResponseEntity testtryLock2(){
        System.out.println("=== 开始"  + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
        RLock lock = redissonClient.getLock("lock");

        try {
            // 锁等待时间：3秒   获取锁5秒后自动解锁
            if(lock.tryLock(3,5,TimeUnit.SECONDS)) {
                System.out.println("=== 锁住" + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
                Thread.sleep(2000);
            }else{
                System.out.println("=== 没有获取到锁" + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try{
                lock.unlock();
                System.out.println("=== 解锁" + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
            }catch (Exception e){
                System.out.println("=== 未能解锁" + "|" + Thread.currentThread().getName() + "|" + new SimpleDateFormat("hh:MM:ss").format(new Date()));
            }


        }

        return ResponseEntity.ok(Math.random());
    }
}
