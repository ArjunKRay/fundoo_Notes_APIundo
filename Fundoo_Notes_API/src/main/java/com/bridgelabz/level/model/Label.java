package com.bridgelabz.level.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.notes.model.Note;

@Document(collection = "Lavel")
public class Label {

	@Id
	private String lavelId;
	private String lavelName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Note note;
	private String userId;

	@DBRef
	private List<Note> notes;

	public Label() {
	}

	public Label(String lavelName, String userId, LocalDateTime createdDate, LocalDateTime updatedDate, Note note,
			List<Note> notes) {
		this.lavelName = lavelName;

		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.note = note;
		this.notes = notes;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLavelName() {
		return lavelName;
	}

	public void setLavelName(String lavelName) {
		this.lavelName = lavelName;
	}

	public String getLevelId() {
		return lavelId;
	}

	public void setLevelId(String lavelId) {
		this.lavelId = lavelId;
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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

}
