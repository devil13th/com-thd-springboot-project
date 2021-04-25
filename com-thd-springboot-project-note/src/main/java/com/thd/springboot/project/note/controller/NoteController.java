package com.thd.springboot.project.note.controller;


import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.model.Message;
import com.thd.springboot.framework.utils.UuidUtils;
import com.thd.springboot.framework.web.BasicController;
import com.thd.springboot.project.note.entity.NoteEntity;
import com.thd.springboot.project.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * controller.NoteController
 **/
@RestController
@RequestMapping("/note")
public class  NoteController extends BasicController {

	@Autowired
	private NoteService noteService;

	@ResponseBody
    @PostMapping("/addNote")
    // url : http://127.0.0.1:8899/thd/cg/addNote
    public Message addNote(@RequestBody NoteEntity entity){
        entity.setNoteId(UuidUtils.uuid());
        this.noteService.insert(entity);
        return Message.success(entity);
    }

    @ResponseBody
    @PostMapping("/updateNote")
    // url : http://127.0.0.1:8899/thd/cg/updateNote
    public Message updateNote(@RequestBody NoteEntity entity){
        int updateCount = this.noteService.update(entity);
        if(updateCount!=1){
            throw new RuntimeException(" Update Failed !");
        }
        return Message.success("SUCCESS");
    }

    @ResponseBody
    @DeleteMapping("/physicsDeleteNote/{id}")
    // url : http://127.0.0.1:8899/thd/cg/physicsDeleteNote/15
    public Message physicsDeleteNote(@PathVariable String id){
        this.noteService.deletePhysicsById(id);
        return Message.success("SUCCESS");
    }

    @ResponseBody
    @DeleteMapping("/logicDeleteNote/{id}")
    // url : http://127.0.0.1:8899/thd/cg/logicDeleteNote/15
    public Message logicDeleteNote(@PathVariable String id){
        this.noteService.deleteLogicById(id);
        return Message.success("SUCCESS");
    }


    @ResponseBody
    @DeleteMapping("/deleteLogicByNoteIds")
    // url : http://127.0.0.1:8899/thd/cg/deleteLogicByNoteIds
    public Message deleteLogicByNoteIds(@RequestBody List<String> ids){
        List<Object> idObjList = new ArrayList<Object>();
        ids.forEach(id -> idObjList.add(id));
        this.noteService.deleteLogicByIds(idObjList);
        return Message.success("SUCCESS");
    }

    @ResponseBody
    @RequestMapping("/queryNoteById/{id}")
    // url : http://127.0.0.1:8899/thd/cg/queryNoteById/2
    public Message queryNote(@PathVariable String id){
        NoteEntity entity = this.noteService.queryById(id);
        return Message.success(entity);
    }

    @ResponseBody
    @RequestMapping("/queryNoteEqByPage")
    // url : http://127.0.0.1:8899/thd/cg/queryNoteEqByPage
    public Message queryNoteEqByPage(NoteEntity entity){
        PageInfo pi = this.noteService.queryListEqByPage(entity);
        return Message.success(pi);
    }

    @ResponseBody
    @RequestMapping("/queryNoteLikeByPage")
    // url : http://127.0.0.1:8899/thd/cg/queryNoteLikeByPage
    public Message queryNoteLikeByPage(NoteEntity entity){
        PageInfo pi = this.noteService.queryListLikeByPage(entity);
        return Message.success(pi);
    }



}

