package com.bridgelabz.notes.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.level.model.Level;

import org.springframework.data.annotation.Id;


@Document(collection="note")

public class Note {
	
     @Id
	private String Id;
	private String userId;
	private String tittle;
	private String description; 
	private String images;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private boolean isPin;
	private boolean isTrace;
	private boolean isArchive;
	
	
	@DBRef
	private List<Level>listLevel;

	public Note() {}
	
	public Note(String id, String userId, String tittle, String description, LocalDateTime createdDate,
			LocalDateTime updatedDate, boolean isPin, boolean isTrace, boolean isArchive) {
	
		Id = id;
		this.userId = userId;
		this.tittle = tittle;
		this.description = description;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.isPin = isPin;
		this.isTrace = isTrace;
		this.isArchive = isArchive;
	}
	


	public String getId() {
		return Id;
	}
	
	public void setId(String id) {
		Id = id;
	}
	
	public String getUserId() 
	{
		return userId;
	}
	
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getTittle() 
	{
		return tittle;
	}
	
	public void setTittle(String tittle) 
	{
		this.tittle = tittle;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public LocalDateTime getCreatedDate() 
	{
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) 
	{
		this.createdDate = createdDate;
	}
	
	public LocalDateTime getUpdatedDate() 
	{
		return updatedDate;
	}
	
	public void setUpdatedDate(LocalDateTime updatedDate) 
	{
		this.updatedDate = updatedDate;
	}
	
	public boolean isPin() 
	{
		return isPin;
	}
	public void setPin(boolean isPin) 
	{
		this.isPin = isPin;
	}
	public boolean isTrace() {
		return isTrace;
	}
	
	public void setTrace(boolean isTrace) 
	{
		this.isTrace = isTrace;
	}
	public boolean isArchive()
	{
		return isArchive;
	}
	
	public void setArchive(boolean isArchive)
	{
		this.isArchive = isArchive;
	}



	@Override
	public String toString() {
		return "Note [Id=" + Id + ", userId=" + userId + ", tittle=" + tittle + ", description=" + description
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", isPin=" + isPin + ", isTrace="
				+ isTrace + ", isArchive=" + isArchive + ", note="  + "]";
	}
	

}
