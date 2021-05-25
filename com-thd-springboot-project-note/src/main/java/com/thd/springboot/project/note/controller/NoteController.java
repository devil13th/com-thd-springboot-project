package com.thd.springboot.project.note.controller;


import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.model.Message;
import com.thd.springboot.framework.utils.MyStringUtils;
import com.thd.springboot.framework.utils.UuidUtils;
import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.springboot.project.note.entity.NoteEntity;
import com.thd.springboot.project.note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * controller.NoteController
 **/
@RestController
@RequestMapping("/note")
public class  NoteController extends BasicController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NoteService noteService;
//    @Autowired
//    private RestHighLevelClient esClient;
//    @Autowired
//    private NoteEsService noteEsService;



//    @ResponseBody
//    @GetMapping("/search")
//    // url : http://127.0.0.1:8899/thd/note/search
//    public Message search(@RequestParam String keyWords,@RequestParam(required = false) String classify) throws Exception{
//        return Message.success(this.noteEsService.search(keyWords,classify));
//    }

//    @ResponseBody
//    @GetMapping("/createNoteIndex")
//    // url : http://127.0.0.1:8899/thd/note/createNoteIndex
//    public Message createNoteIndex() throws Exception{
//        return Message.success(this.noteEsService.createIndex());
//    }


//    @ResponseBody
//    @GetMapping("/deleteNodeIndex")
//    // url : http://127.0.0.1:8899/thd/note/deleteNodeIndex
//    public Message deleteNodeIndex() throws Exception{
//        return Message.success(this.noteEsService.deleteIndex());
//    }






	@ResponseBody
    @PostMapping("/addNote")
    // url : http://127.0.0.1:8899/thd/note/addNote
    public Message addNote(@RequestBody NoteEntity entity) throws Exception{
        entity.setNoteId(UuidUtils.uuid());
        this.noteService.insert(entity);
//        this.noteEsService.index(entity);
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

    @ResponseBody
    @RequestMapping("/queryNoteNoPage")
    // url : http://127.0.0.1:8899/thd/cg/queryNoteNoPage
    public Message queryNoteNoPage(NoteEntity entity){
        List<NoteEntity> l = this.noteService.queryLike(entity);
        return Message.success(l);
    }

    @ResponseBody
    @RequestMapping("/toggleNoteState/{id}")
    public Message toggleNoteState(@PathVariable String id){
        NoteEntity noteEntity = this.noteService.queryById(id);
        if("Todo".equals(noteEntity.getClassify())){

            if(null == noteEntity.getTodoStatus()){
                noteEntity.setTodoStatus(1);
            }else if (1 == noteEntity.getTodoStatus()){
                noteEntity.setTodoStatus(0);
            }else if (0 == noteEntity.getTodoStatus()){
                noteEntity.setTodoStatus(1);
            }
            this.noteService.update(noteEntity);
        }else{
            throw new RuntimeException("Classify is not 'Todo'");
        }
        return Message.success(CommonConstants.STATUS_SUCCESS);

    }

    @ResponseBody
    @PostMapping("/finishTodo")
    public Message finishTodo(@RequestBody NoteEntity entity){
       if(MyStringUtils.isEmpty(entity.getNoteId())){
           throw new RuntimeException("Note id is empty");
       }
       if(null == entity.getFinishTime()){
           throw new RuntimeException("Finish time is empty");
       }
       entity.setTodoStatus(1);
       this.noteService.update(entity);
       return Message.success(CommonConstants.STATUS_SUCCESS);

    }



    /**
     * 处理单个文件上传
     * @param file
     * @param keyword
     * @return
     */
    @PostMapping("/singleUpload")
    @ResponseBody
    // url : http://127.0.0.1:8899/note/singleUpload
    public String singleUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D://deleteme/upload//";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            logger.error(e.toString(), e);
            return "上传失败!" + e.getMessage();
        }
    }

}

