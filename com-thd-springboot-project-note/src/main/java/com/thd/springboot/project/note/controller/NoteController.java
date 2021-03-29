package com.thd.springboot.project.note.controller;

import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.model.Message;
import com.thd.springboot.framework.web.BasicController;
import com.thd.springboot.project.note.entity.NoteEntity;
import com.thd.springboot.project.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * com.thd.springboot.project.note.controller.NoteController
 *
 * @author: wanglei62
 * @DATE: 2021/3/26 17:16
 **/
@Controller
@RequestMapping("/note")
public class NoteController extends BasicController {
    @RequestMapping("/test")
    @ResponseBody
    public Message test(){
        this.getLogger().info("test...");
        return Message.success(CommonConstants.STATUS_SUCCESS);
    }


    @Autowired
    private NoteService noteService;

    /**
     * 添加数据
     */
    @PostMapping(value = "/insertNote")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/note/insertNote
    public Message insertNote(@RequestBody NoteEntity note) {
        // 生成主键
        note.setNoteId(UUID.randomUUID().toString().replace("-",""));
        noteService.insert(note);
        return success(note);
    }

    /**
     * 修改数据
     *
     * @param  note
     * @return
     */
    @PutMapping(value = "/updateNote")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/note/updateNote
    public Message updateNote(@RequestBody NoteEntity  note) {
        noteService.update(note);
        return success(note);
    }

    /**
     * 获取数据
     *
     * @param noteId
     * @return
     */
    @GetMapping("/queryNoteById/{noteId}")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/note/queryNoteById
    public Message queryNoteById(@PathVariable String noteId) {
        NoteEntity  note  =  noteService.queryById(noteId);
        if( note == null){
            return error("No qualifying record!");
        }
        return success(note);
    }

    /**
     * 列表数据
     *
     * @param  condition
     * @return
     */
    // url : http://127.0.0.1:8899/thd/note/findNotePage
    @GetMapping("/findNotePage")
    @ResponseBody
    public Message findNotePage(NoteEntity condition) {
        PageInfo<NoteEntity> pager = noteService.queryListLikeByPage(condition);
        return success(pager);
    }

    /**
     * 删除数据
     *
     * @param noteId
     * @return
     */
    @DeleteMapping("/deleteNote/{noteId}")
    @ResponseBody
    // url : http://127.0.0.1:8899/thd/note/deleteNote
    public Message deleteNote(@PathVariable String noteId) {
        noteService.deleteLogicById(noteId);
        return success();
    }

    /**
     * 批量新增
     * @param list
     * @return
     */
    @PostMapping("/insertNoteBatch")
    @ResponseBody
    public Message insertNoteBatch(@RequestBody List<NoteEntity> list) {
        noteService.insertBatch(list);
        return success();
    }

    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    @GetMapping("/queryNoteByCondition")
    @ResponseBody
    public Message queryNoteByCondition(NoteEntity condition) {
        NoteEntity entity = noteService.queryByCondition(condition);
        return success(entity);
    }
}
