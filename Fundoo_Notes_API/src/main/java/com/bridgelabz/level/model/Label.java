package com.bridgelabz.level.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.notes.model.Note;

@Document(collection = "Label")
public class Label {

	@Id
	private String labelId;
	private String labelName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Note note;
	private String userId;
	
	public Label() {
	}

	public Label(String labelName, String userId, LocalDateTime createdDate, LocalDateTime updatedDate, Note note,
			List<Note> notes) {
		this.labelName = labelName;

		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.note = note;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}


	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

}
