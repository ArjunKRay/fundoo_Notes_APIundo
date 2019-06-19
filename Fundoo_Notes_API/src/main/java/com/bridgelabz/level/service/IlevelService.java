package com.bridgelabz.level.service;

import java.util.List;
import com.bridgelabz.level.dto.LevelDto;
import com.bridgelabz.level.model.Level;
import com.bridgelabz.response.Response;


public interface IlevelService {
	
   Response createLevel(LevelDto levelDto,String tocken);
   Response deleteLevel(String tocken,String levelId);
   Response updateLevel(LevelDto levelDto,String tocken,String levelId);
   List<Level>getAllLevel(String tocken);

}
