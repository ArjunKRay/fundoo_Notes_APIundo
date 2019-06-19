package com.bridgelabz.level.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.exception.UserException;
import com.bridgelabz.level.dto.LevelDto;
import com.bridgelabz.level.model.Level;
import com.bridgelabz.level.repository.ILevelRepository;
import com.bridgelabz.response.Response;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.repository.UserRepository;
import com.bridgelabz.utility.ITockenGenerator;



@Service
public class ServiceLevelImpl implements IlevelService {

	
	@Autowired
	ITockenGenerator tockenGenerator;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ILevelRepository levelRepository;
	
	@Override
	public Response createLevel(LevelDto levelDto, String tocken) {
	   String id = tockenGenerator.verifyTocken(tocken);
	   Optional<User> optionalUser= userRepository.findById(id);
	  
	   if(optionalUser.isPresent()) {
		    Level levelSaved = modelMapper.map(levelDto,Level.class) ;
		    levelSaved.setUserId(id);
		    levelSaved.setLevelName(levelDto.getLevelName());
		    levelSaved.setCreatedDate(LocalDateTime.now());
		    levelRepository.save(levelSaved);
		    return new Response(200,"Level created successfully",null); 
	       }
	   else {
		    throw new UserException("Level not created");
	   }
	}

	@Override
	public Response deleteLevel(String tocken, String levelId) {
	  String ide = tockenGenerator.verifyTocken(tocken);
	  Optional<Level> optionalLevel= levelRepository.findByLevelIdAndUserId(ide,levelId);
	    if(optionalLevel.isPresent()) {
		  Level levelSaved = optionalLevel.get();
		  levelRepository.delete(levelSaved);
		  return new Response(200,"level deleted",null);
	     }
	  throw new UserException("level deleted");
	}

	@Override
	public Response updateLevel(LevelDto levelDto, String tocken, String levelId) {
		 String ide = tockenGenerator.verifyTocken(tocken);
		 Optional<Level>optionalLevel =levelRepository.findByLevelIdAndUserId(levelId,ide);
         
		 if(optionalLevel.isPresent())
          {
        	  Level levelSaved = optionalLevel.get();
        	  levelSaved.setLevelName(levelDto.getLevelName());
        	  levelSaved.setUpdatedDate(LocalDateTime.now());
        	  levelRepository.save(levelSaved);
        	   return new Response(200,"level updated",null);  
          }
		 else {
			   throw new UserException("level not Updated");
		      }
	}    	  
	@Override
	public List<Level> getAllLevel(String tocken) {
		// TODO Auto-generated method stub
		return null;
	}

}
