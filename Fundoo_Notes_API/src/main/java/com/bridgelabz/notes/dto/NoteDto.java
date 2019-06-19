package com.bridgelabz.notes.dto;

public class NoteDto {
	
	private String tittle;
	private String description;
	
	public NoteDto() {}

	public NoteDto(String tittle, String description) {
		this.tittle = tittle;
		this.description = description;
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

	@Override
	public String toString() {
		return "NoteDto [tittle=" + tittle + ", description=" + description + "]";
	}

	

}
