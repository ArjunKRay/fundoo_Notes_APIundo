package com.bridgelabz.fundooApp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundooApp.dto.LabelDto;
import com.bridgelabz.fundooApp.exception.LabelException;
import com.bridgelabz.fundooApp.exception.NoteException;
import com.bridgelabz.fundooApp.exception.UserException;
import com.bridgelabz.fundooApp.model.Label;
import com.bridgelabz.fundooApp.model.Note;
import com.bridgelabz.fundooApp.model.User;
import com.bridgelabz.fundooApp.repository.LabelRespository;
import com.bridgelabz.fundooApp.repository.NoteRepository;
import com.bridgelabz.fundooApp.repository.UserRepository;
import com.bridgelabz.fundooApp.utility.JWTTokenGenerator;

@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	private JWTTokenGenerator tokenGenerator;
	@Autowired
	private LabelRespository labelRespository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private NoteRepository noteRepository;

	@Override
	public String createLabel(String token, LabelDto labelDto) {
		String userId = tokenGenerator.verifyToken(token);
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isPresent()) {
			User user = optUser.get();
			Label label = modelMapper.map(labelDto, Label.class);
			label.setCreationTime(LocalDateTime.now());
			label.setUpdateTime(LocalDateTime.now());
			label.setUserId(user.getUserId());
			labelRespository.save(label);
			return "label created";
		} else {
			throw new UserException("User not present");
		}

	}

	@Override
	public String updateLabel(String token, String labelId, LabelDto labelDto) {
		String userId = tokenGenerator.verifyToken(token);
	
			Optional<Label> optLabel=labelRespository.findByLabelIdAndUserId(labelId, userId);
			if(optLabel.isPresent())
			{
				Label label = optLabel.get();
				label.setLabelName(labelDto.getLabelName());
				label.setUpdateTime(LocalDateTime.now());
				labelRespository.save(label);
			
				return "label updated";

			} else {
				
				throw new LabelException("label id or user dont match");
			}
	
	}

	@Override
	public String deleteLabel(String token, String labelId) {

		String userId = tokenGenerator.verifyToken(token);
		Optional<Label> optLabel=labelRespository.findByLabelIdAndUserId(labelId, userId);
		if(optLabel.isPresent())
		{
			
				Label label = optLabel.get();
				labelRespository.delete(label);
				return "label deleted";

			} else {

				throw new LabelException("label doesnt exist");
			}
		
	}

	@Override
	public List<Label> getAllLabel(String token) {
		String userId = tokenGenerator.verifyToken(token);
		List<Label> labels = labelRespository.findByUserId(userId);

		return labels;
	}

	@Override
	public String addLabelToNote(String token, String labelId, String noteId) {

		String userId = tokenGenerator.verifyToken(token);
		Optional<Label> optLabel = labelRespository.findByLabelIdAndUserId(labelId, userId);
		if (optLabel.isPresent()) {
			Optional<Note> optNote = noteRepository.findById(noteId);
			if (optNote.isPresent()) {
				Note note = optNote.get();
				List<Label> labelList = new ArrayList<Label>();
				Label label = optLabel.get();
			
				if (note.getLabels() != null) {
					labelList = note.getLabels();

					labelList.add(label);

				} else {
					labelList.add(label);
				}
				note.setLabels(labelList);
				note.setUpdateTime(LocalDateTime.now());
				noteRepository.save(note);
				return "label added to note";

			} else {
				throw new NoteException("note not present");
			}
		} else {
			throw new UserException("User or label not present");
		}

	}

	@Override
	public String removeLabelFromNote(String token, String labelId, String noteId) {

		String userId = tokenGenerator.verifyToken(token);
		Optional<Label> optLabel = labelRespository.findByLabelIdAndUserId(labelId, userId);
		if (optLabel.isPresent()) {
			Optional<Note> optNote = noteRepository.findById(noteId);
			if (optNote.isPresent()) {
				Note note = optNote.get();
				Label label = optLabel.get();
				List<Label> labelList = new ArrayList<Label>();
				if (note.getLabels() != null) {
					labelList = note.getLabels();
					for (Label label1 : labelList) {
						if (label1.getLabelId().equals(label.getLabelId())) {
							labelList.remove(label1);
							note.setUpdateTime(LocalDateTime.now());
							note.setLabels(labelList);
							noteRepository.save(note);

							return "label removed from note";

						}
					}
				}

				return "label remove from note";

			} else {
				throw new NoteException("note note present");
			}
		} else {
			throw new UserException("User or label not found");
		}
	}

	@Override
	public Label getLabel(String token, String labelId) {
		String userId = tokenGenerator.verifyToken(token);
		Optional<Label> optLabel=labelRespository.findByLabelIdAndUserId(labelId, userId);
		if(optLabel.isPresent())
		{
				Label label = optLabel.get();
				return label;
			} else {
				throw new LabelException("label or User not present");
			}
		
	}

}
