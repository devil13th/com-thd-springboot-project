package com.thd.springboot.project.note.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thd.springboot.framework.entity.BasicEntity;
import lombok.Data;

import java.util.Date;


@Data
public class  NoteEntityParent extends BasicEntity<String> {
    // 
    private String noteId;
    // 
    private String classify;
    // 
    private String title;
    // 
    private String content;
    // 
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expireDate;
    // 
    private Integer alarmDays;
    // 
    private Integer todoLevel;
    // 
    private Integer todoStatus;
    //
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;
    //
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date finishTime;
}
