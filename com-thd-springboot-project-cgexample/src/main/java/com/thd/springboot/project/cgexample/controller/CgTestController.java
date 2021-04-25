package com.thd.springboot.project.cgexample.controller;


import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.model.Message;
import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.springboot.project.cgexample.entity.CgTestEntity;
import com.thd.springboot.project.cgexample.service.CgTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * controller.CgTestController
 **/
@RestController
@RequestMapping("/cgTest")
public class  CgTestController extends BasicController {

	@Autowired
	private  CgTestService  cgTestService;

	@ResponseBody
	@PostMapping("/addCgTest")
	// url : http://127.0.0.1:8899/thd/cg/addCgTest
	public Message addCgTest(@RequestBody CgTestEntity entity){
		entity.setId(UUID.randomUUID().toString());
		this.cgTestService.insert(entity);
		return Message.success("SUCCESS");
	}

	@ResponseBody
	@PostMapping("/updateCgTest")
	// url : http://127.0.0.1:8899/thd/cg/updateCgTest
	public Message updateCgTest(@RequestBody CgTestEntity entity){
		int updateCount = this.cgTestService.update(entity);
		if(updateCount!=1){
			throw new RuntimeException(" Update Failed !");
		}
		return Message.success("SUCCESS");
	}

	@ResponseBody
	@DeleteMapping("/physicsDeleteCgTest/{id}")
	// url : http://127.0.0.1:8899/thd/cg/physicsDeleteCgTest/15
	public Message physicsDeleteCgTest(@PathVariable String id){
		this.cgTestService.deletePhysicsById(id);
		return Message.success("SUCCESS");
	}

	@ResponseBody
	@DeleteMapping("/logicDeleteCgTest/{id}")
	// url : http://127.0.0.1:8899/thd/cg/logicDeleteCgTest/15
	public Message logicDeleteCgTest(@PathVariable String id){
		this.cgTestService.deleteLogicById(id);
		return Message.success("SUCCESS");
	}


	@ResponseBody
	@DeleteMapping("/deleteLogicByCgTestIds")
	// url : http://127.0.0.1:8899/thd/cg/deleteLogicByCgTestIds
	public Message deleteLogicByCgTestIds(@RequestBody List<String> ids){
		List<Object> idObjList = new ArrayList<Object>();
		ids.forEach(id -> idObjList.add(id));
		this.cgTestService.deleteLogicByIds(idObjList);
		return Message.success("SUCCESS");
	}

	@ResponseBody
	@RequestMapping("/queryCgTestById/{id}")
	// url : http://127.0.0.1:8899/thd/cg/queryCgTestById/2
	public Message queryCgTest(@PathVariable String id){
		CgTestEntity entity = this.cgTestService.queryById(id);
		return Message.success(entity);
	}

	@ResponseBody
	@RequestMapping("/queryCgTestEqByPage")
	// url : http://127.0.0.1:8899/thd/cg/queryCgTestEqByPage
	public Message queryCgTestEqByPage(CgTestEntity entity){
		PageInfo pi = this.cgTestService.queryListEqByPage(entity);
		return Message.success(pi);
	}

	@ResponseBody
	@RequestMapping("/queryCgTestLikeByPage")
	// url : http://127.0.0.1:8899/thd/cg/queryCgTestLikeByPage
	public Message queryCgTestLikeByPage(CgTestEntity entity){
		PageInfo pi = this.cgTestService.queryListLikeByPage(entity);
		return Message.success(pi);
	}

	@ResponseBody
	@RequestMapping("/insertCgTestBatch")
	// url : http://127.0.0.1:8899/thd/cg/insertCgTestBatch
	public Message insertCgTestBatch(){

		List<CgTestEntity> l = new ArrayList<CgTestEntity>();
		for(int i = 0 , j = 10 ; i < j ; i++){
			CgTestEntity entity = new CgTestEntity();
			entity.setId("id_" + i );
			entity.setUserAge(i);
			entity.setUserBirthday(new Date());
			entity.setUserName("devil13th_" + i);
			l.add(entity);

		}
		this.cgTestService.insertBatch(l);
		return Message.success("Success");
	}

}

