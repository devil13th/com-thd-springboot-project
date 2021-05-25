package com.thd.springboot.project.note.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class NoteEntity extends NoteEntityParent {
	private static final long serialVersionUID = 1L;
	private String highLight;

	private String keyWords;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date expireDateFrom;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date expireDateTo;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	private Date finishTimeFrom;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	private Date finishTimeTo;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	private Date startTimeFrom;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	private Date startTimeTo;
}

