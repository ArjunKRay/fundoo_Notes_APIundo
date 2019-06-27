package com.bridgelabz.level.service;

import java.util.List;
import com.bridgelabz.level.dto.LavelDto;
import com.bridgelabz.response.Response;

public interface IlavelService {

	Response createLavel(LavelDto lavelDto, String tocken);

	Response deleteLavel(String tocken, String lavelId);

	Response updateLavel(LavelDto lavelDto, String tocken, String lavelId);

	List<LavelDto> getAllLavel(String tocken);

}
