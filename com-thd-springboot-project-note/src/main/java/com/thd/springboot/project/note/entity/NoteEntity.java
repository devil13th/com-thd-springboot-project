package com.thd.springboot.project.note.entity;


import lombok.Data;

import java.util.Date;

@Data
public class NoteEntity extends NoteEntityParent {
	private static final long serialVersionUID = 1L;
	private String highLight;

	private Date expireDateFrom;
	private Date expireDateTo;

	private Date finishTimeFrom;
	private Date finishTimeTo;

	private Date startTimeFrom;
	private Date startTimeTo;
}

