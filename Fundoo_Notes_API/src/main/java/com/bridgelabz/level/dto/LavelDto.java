package com.bridgelabz.level.dto;

public class LavelDto {

	private String lavelName;

	public LavelDto()
	{
	}

	public LavelDto(String lavelName) {
		this.lavelName = lavelName;
	}

	public String getLavelName() {
		return lavelName;
	}

	public void setLavelName(String levelName) {
		this.lavelName = levelName;
	}

	@Override
	public String toString() {
		return "LevelDto [levelName=" + lavelName + "]";
	}

}
