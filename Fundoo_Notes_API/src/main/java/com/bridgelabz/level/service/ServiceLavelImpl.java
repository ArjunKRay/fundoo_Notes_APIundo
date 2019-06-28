package com.bridgelabz.level.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.exception.LevelException;
import com.bridgelabz.level.dto.LavelDto;
import com.bridgelabz.level.model.Label;
import com.bridgelabz.level.repository.ILavelRepository;
import com.bridgelabz.response.Response;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.repository.UserRepository;
import com.bridgelabz.utility.ITockenGenerator;

@Service
public class ServiceLavelImpl implements IlavelService {

	@Autowired
	ITockenGenerator tockenGenerator;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ILavelRepository lavelRepository;

	@Override
	public Response createLavel(LavelDto lavelDto, String tocken) {
		String id = tockenGenerator.verifyTocken(tocken);
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			Label lavelSaved = modelMapper.map(lavelDto, Label.class);
			lavelSaved.setUserId(id);
			lavelSaved.setLavelName(lavelDto.getLavelName());
			lavelSaved.setCreatedDate(LocalDateTime.now());
			lavelRepository.save(lavelSaved);
			return new Response(200, "Level created successfully", null);
		} else {
			throw new LevelException("User Id not  Present");
		}
	}

	@Override
	public Response deleteLavel(String tocken, String lavelId) {
		
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Label> optionalLavel = lavelRepository.findById(lavelId);
			if (optionalLavel.isPresent()) {
				Label lavelSaved = optionalLavel.get();
				lavelRepository.delete(lavelSaved);
				return new Response(200, "level deleted", null);
			} else {
				throw new LevelException("level id Not Present");
			}
		} else {
			throw new LevelException("User id not Present");
		}
	}

	@Override
	public Response updateLavel(LavelDto lavelDto, String tocken, String lavelId) {
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Label> optionalLevel = lavelRepository.findById(lavelId);
			if (optionalLevel.isPresent()) {
				Label levelSaved = optionalLevel.get();
				levelSaved.setLavelName(lavelDto.getLavelName());
				levelSaved.setUpdatedDate(LocalDateTime.now());
				lavelRepository.save(levelSaved);
				return new Response(200, "level updated", null);
			} else {
				throw new LevelException("level not Updated");
			}
		} else {
			throw new LevelException("User id not present");
		}
	}

	@Override
	public List<LavelDto> getAllLavel(String tocken) {
		String id = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(id);
		if (isUserId.isPresent()) {
			List<Label> lavel = lavelRepository.findAll();
			List<LavelDto> lavels = new ArrayList<>();
			for (Label lavelList : lavel) {
				LavelDto lavelDto = modelMapper.map(lavelList, LavelDto.class);
			}
			return lavels;
		}
		return null;

	}
}