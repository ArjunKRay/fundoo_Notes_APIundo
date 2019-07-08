package com.bridgelabz.level.service;

import java.util.List;
import com.bridgelabz.level.dto.LabelDto;
import com.bridgelabz.level.model.Label;
import com.bridgelabz.response.Response;

public interface IlabelService {

	Response createLabel(LabelDto labelDto, String tocken);

	Response deleteLabel(String tocken, String labelId);

	Response updateLabel(LabelDto labelDto, String tocken, String labelId);

	List<Label> getAllLabel(String tocken);

}
