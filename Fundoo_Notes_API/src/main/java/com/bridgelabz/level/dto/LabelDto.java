package com.bridgelabz.level.dto;

public class LabelDto {

	private String labelName;

	public LabelDto()
	{
	}

	public LabelDto(String labelName) {
		this.labelName = labelName;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String lebelName) {
		this.labelName = lebelName;
	}

	@Override
	public String toString() {
		return "LebelDto [lebelName=" + labelName + "]";
	}



}
