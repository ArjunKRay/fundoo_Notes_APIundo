package com.bridgelabz.level.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.exception.LabelException;
import com.bridgelabz.level.dto.LabelDto;
import com.bridgelabz.level.model.Label;
import com.bridgelabz.level.repository.ILabelRepository;
import com.bridgelabz.response.Response;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.repository.UserRepository;
import com.bridgelabz.utility.ITockenGenerator;

@Service
public class ServiceLabelImpl implements IlabelService {

	@Autowired
	ITockenGenerator tockenGenerator;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ILabelRepository labelRepository;

	@Override
	public Response createLabel(LabelDto labelDto, String tocken) {
		String id = tockenGenerator.verifyTocken(tocken);
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			Label lavelSaved = modelMapper.map(labelDto, Label.class);
			lavelSaved.setUserId(id);
			lavelSaved.setLabelName(labelDto.getLabelName());
			lavelSaved.setCreatedDate(LocalDateTime.now());
			labelRepository.save(lavelSaved);
			return new Response(200, "Level created successfully", null);
		} else {
			throw new LabelException("User Id not  Present");
		}
	}

	@Override
	public Response deleteLabel(String tocken, String labelId) {

		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Label> optionalLavel = labelRepository.findById(labelId);
			if (optionalLavel.isPresent()) {
				Label lavelSaved = optionalLavel.get();
				labelRepository.delete(lavelSaved);
				return new Response(200, "label deleted", null);
			} else {
				throw new LabelException("label id Not Present");
			}
		} else {
			throw new LabelException("User id not Present");
		}
	}

	@Override
	public Response updateLabel(LabelDto labelDto, String tocken, String labelId) {
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> optionalUser = userRepository.findById(ide);
		if (optionalUser.isPresent()) {
			Optional<Label> optionalLabel = labelRepository.findById(labelId);
			if (optionalLabel.isPresent()) {
				Label levelSaved = optionalLabel.get();
				levelSaved.setLabelName(labelDto.getLabelName());
				levelSaved.setUpdatedDate(LocalDateTime.now());
				labelRepository.save(levelSaved);
				return new Response(200, "level updated", null);
			} else {
				throw new LabelException("level not Updated");
			}
		} else {
			throw new LabelException("User id not present");
		}
	}

	@Override
	public List<Label> getAllLabel(String tocken) {
		String id = tockenGenerator.verifyTocken(tocken);
		Optional<User> optUser = userRepository.findById(id);
		List<Label> labelList = new ArrayList<Label>();
		if (optUser.isPresent()) {
			User user = optUser.get();
			for (Label labelLists :labelList) {
				
			}
		}

		return labelList;
	}

}