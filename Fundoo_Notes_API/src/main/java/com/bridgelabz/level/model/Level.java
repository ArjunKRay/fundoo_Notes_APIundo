package com.bridgelabz.level.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.notes.model.Note;

@Document(collection="level")
public class Level {
	
	@Id
	private String levelId;
	private String levelName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Note note;
	private String userId;
	
	@DBRef
	private List<Note>notes;
	
	public Level() {}
	
	public Level(String levelName, String levelId,String userId, LocalDateTime createdDate, LocalDateTime updatedDate, Note note,
			List<Note> notes) {
		this.levelName = levelName;
		this.levelId = levelId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.note = note;
		this.notes = notes;
		this.userId=userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLevelName() {
		return levelName;
	}
	
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public String getLevelId() {
		return levelId;
	}
	
	public void setLevelId(String levelId) {
		this.levelId = levelId;
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
