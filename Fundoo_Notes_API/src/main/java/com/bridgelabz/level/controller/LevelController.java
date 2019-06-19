package com.bridgelabz.level.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.level.dto.LevelDto;
import com.bridgelabz.level.service.IlevelService;
import com.bridgelabz.response.Response;

@RestController
@RequestMapping("/level")
public class LevelController {
	
	
	@Autowired(required = true)
	IlevelService levelService;
	
	@PostMapping("/create")
	public ResponseEntity<Response>create(@RequestBody LevelDto levelDto, String tocken )
	{
		Response response = levelService.createLevel(levelDto, tocken);
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response>delete(@RequestBody String tocken, String levelId )
	{
		Response response = levelService.deleteLevel(tocken, levelId);
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	
     @PutMapping("/update")
	public ResponseEntity<Response>update(@RequestBody LevelDto levelDto, String tocken,String levelId)
	{
		Response response = levelService.createLevel(levelDto, tocken);
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
}
