package com.thd.springboot.project.note.entity.parent;

import lombok.Data;
import com.thd.springboot.framework.entity.BasicEntity;
import java.util.Date;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
public class  NoteEntityParent extends BasicEntity {
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
}
