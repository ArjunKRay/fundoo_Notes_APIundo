package com.bridgelabz.level.dto;

public class LevelDto {
	
	private String levelName;
	
	public LevelDto() {}

	public LevelDto(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Override
	public String toString() {
		return "LevelDto [levelName=" + levelName + "]";
	}
	
	
 
}
