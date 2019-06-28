package com.bridgelabz.notes.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.level.model.Label;
import com.bridgelabz.user.model.User;

@Document(collection = "Note")
public class Note {

	@Id
	private String noteId;
	private String userId;
	private String tittle;
	private String description;
//	private String images;
//	private String reminder;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private boolean isPin;
	private boolean isTrash;
	private boolean isArchive;

	@DBRef
	private List<User> userList;
	@DBRef
	private List<Label> lavelList;
	
	public Note() {}

	public Note(String userId, String tittle, String description, LocalDateTime createdDate,
			LocalDateTime updatedDate, boolean isPin, boolean isTrash, boolean isArchive) {

		this.userId = userId;
		this.tittle = tittle;
		this.description = description;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.isPin = isPin;
		this.isTrash = isTrash;
		this.isArchive = isArchive;
	}

	public List<Label> getLavelList() {
		return getLavelList();
	}

	public void setLavelList(List<Label> lavelList) {
		this.lavelList = lavelList;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean isPin() {
		return isPin;
	}

	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

//	public String getImages() {
//		return images;
//	}
//
//	public void setImages(String images) {
//		this.images = images;
//	}
//
//	public String getReminder() {
//		return reminder;
//	}
//
//	public void setReminder(String reminder) {
//		this.reminder = reminder;
//	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Note [Id=" + noteId + ", userId=" + userId + ", tittle=" + tittle + ", description=" + description
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", isPin=" + isPin + ", isTrace="
				+ isTrash + ", isArchive=" + isArchive + ", note=" + "]";
	}

}
