package com.thd.springboot.project.cgexample.controller;


import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.model.Message;
import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.springboot.project.cgexample.entity.MyPlanEntity;
import com.thd.springboot.project.cgexample.service.MyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * controller.MyPlanController
 **/
@RestController
@RequestMapping("/myPlan")
public class  MyPlanController extends BasicController {

	@Autowired
	private MyPlanService myPlanService;

	@ResponseBody
    @PostMapping("/addMyPlan")
    // url : http://127.0.0.1:8899/thd/cg/addMyPlan
    public Message addMyPlan(@RequestBody MyPlanEntity entity){
        entity.setId(UUID.randomUUID().toString());
        this.myPlanService.insert(entity);
        return Message.success("SUCCESS");
    }

    @ResponseBody
    @PostMapping("/updateMyPlan")
    // url : http://127.0.0.1:8899/thd/cg/updateMyPlan
    public Message updateMyPlan(@RequestBody MyPlanEntity entity){
        int updateCount = this.myPlanService.update(entity);
        if(updateCount!=1){
            throw new RuntimeException(" Update Failed !");
        }
        return Message.success("SUCCESS");
    }

    @ResponseBody
    @DeleteMapping("/physicsDeleteMyPlan/{id}")
    // url : http://127.0.0.1:8899/thd/cg/physicsDeleteMyPlan/15
    public Message physicsDeleteMyPlan(@PathVariable String id){
        this.myPlanService.deletePhysicsById(id);
        return Message.success("SUCCESS");
    }

    @ResponseBody
    @DeleteMapping("/logicDeleteMyPlan/{id}")
    // url : http://127.0.0.1:8899/thd/cg/logicDeleteMyPlan/15
    public Message logicDeleteMyPlan(@PathVariable String id){
        this.myPlanService.deleteLogicById(id);
        return Message.success("SUCCESS");
    }


    @ResponseBody
    @DeleteMapping("/deleteLogicByMyPlanIds")
    // url : http://127.0.0.1:8899/thd/cg/deleteLogicByMyPlanIds
    public Message deleteLogicByMyPlanIds(@RequestBody List<String> ids){
        List<Object> idObjList = new ArrayList<Object>();
        ids.forEach(id -> idObjList.add(id));
        this.myPlanService.deleteLogicByIds(idObjList);
        return Message.success("SUCCESS");
    }

    @ResponseBody
    @RequestMapping("/queryMyPlanById/{id}")
    // url : http://127.0.0.1:8899/thd/cg/queryMyPlanById/2
    public Message queryMyPlan(@PathVariable String id){
        MyPlanEntity entity = this.myPlanService.queryById(id);
        return Message.success(entity);
    }

    @ResponseBody
    @RequestMapping("/queryMyPlanEqByPage")
    // url : http://127.0.0.1:8899/thd/cg/queryMyPlanEqByPage
    public Message queryMyPlanEqByPage(MyPlanEntity entity){
        PageInfo pi = this.myPlanService.queryListEqByPage(entity);
        return Message.success(pi);
    }

    @ResponseBody
    @RequestMapping("/queryMyPlanLikeByPage")
    // url : http://127.0.0.1:8899/thd/cg/queryMyPlanLikeByPage
    public Message queryMyPlanLikeByPage(MyPlanEntity entity){
        PageInfo pi = this.myPlanService.queryListLikeByPage(entity);
        return Message.success(pi);
    }

    @ResponseBody
    @RequestMapping("/insertMyPlanBatch")
    // url : http://127.0.0.1:8899/thd/cg/insertMyPlanBatch
    public Message insertMyPlanBatch(){

//        List<MyPlanEntity> l = new ArrayList<MyPlanEntity>();
//        for(int i = 0 , j = 10 ; i < j ; i++){
//            MyPlanEntity entity = new MyPlanEntity();
//            entity.setId("id_" + i );
//            entity.setUserAge(i);
//            entity.setUserBirthday(new Date());
//            entity.setUserName("devil13th_" + i);
//            l.add(entity);
//
//        }
//        this.myPlanService.insertBatch(l);
        return Message.success("Success");
    }

}

