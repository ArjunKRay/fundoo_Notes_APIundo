package com.bridgelabz.notes.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.exception.LevelException;
import com.bridgelabz.exception.NoteException;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.level.model.Label;
import com.bridgelabz.level.repository.ILavelRepository;
import com.bridgelabz.notes.dto.NoteDto;
import com.bridgelabz.notes.model.Note;
import com.bridgelabz.notes.repository.NoteRepository;
import com.bridgelabz.response.Response;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.repository.UserRepository;
import com.bridgelabz.utility.ITockenGenerator;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	ITockenGenerator tockenGenerator;
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ILavelRepository levelRepository;

	@Override
	public Response createNote(NoteDto noteDto, String tocken) {

		String id = tockenGenerator.verifyTocken(tocken);
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			Note note = modelMapper.map(noteDto, Note.class);
			note.setUserId(id);
			note.setCreatedDate(LocalDateTime.now());
			note.setUpdatedDate(LocalDateTime.now());
			noteRepository.save(note);
			return new Response(200, "note created successfully", null);
		} else {
			throw new NoteException("UserId not present");
		}
	}

	@Override
	public Response deleteNote(NoteDto noteDto, String tocken, String noteId) {
		if (noteDto.getTittle().isEmpty() && noteDto.getDescription().isEmpty()) {
			throw new NoteException("note is empty");
		}
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> optionalUser = userRepository.findById(ide);
		if (optionalUser.isPresent()) {
			Optional<Note> optionalNote = noteRepository.findByNoteId(noteId);
			if (optionalNote.isPresent()) {
				Note noteSaved = optionalNote.get();
				noteSaved.setTrash(true);
				noteSaved.setUpdatedDate(LocalDateTime.now());
				noteRepository.save(noteSaved);
				return new Response(200, "Note deleted", null);
			} else {
				throw new NoteException("Note Id not Present");
			}
		} else {
			throw new UserException("User Id not present");
		}

	}

	@Override
	public Response updateNote(NoteDto noteDto, String tocken, String noteId) {
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Note> note = noteRepository.findByNoteId(noteId);
			if (note.isPresent()) {
				Note noteSaved = note.get();
				noteSaved.setTittle(noteDto.getTittle());
				noteSaved.setDescription(noteDto.getDescription());
				noteSaved.setUpdatedDate(LocalDateTime.now());
				noteRepository.save(noteSaved);
				return new Response(200, "Updated note successfully", null);
			} else {
				throw new NoteException("note Id not matches");
			}
		} else {
			throw new UserException("User Id not present");
		}
	}

	@Override
	public Response archiveAndUnArchive(String tocken, String noteId) {
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Note> notes = noteRepository.findByNoteId(noteId);
			if (notes.isPresent()) {
				Note noteSaved = notes.get();
				if (noteSaved.isArchive()) {
					noteSaved.setArchive(false);
					noteRepository.save(noteSaved);
					return new Response(200, "Unarchived notes", null);
				} else {
					noteSaved.setArchive(true);
					noteRepository.save(noteSaved);
					return new Response(200, "Archived Notes", null);
				}
			} else {
				throw new NoteException("Note Id not present");
			}
		} else {
			throw new UserException("User Id not Present");
		}

	}

	@Override
	public Response pinAndUnpPin(String tocken, String noteId) {
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Note> notes = noteRepository.findById(noteId);
			if (notes.isPresent()) {
				Note noteSaved = notes.get();
				if (noteSaved.isPin()) {
					noteSaved.setPin(false);
					noteRepository.save(noteSaved);
					return new Response(200, "Notes unpined ", null);
				} else {
					noteSaved.setPin(true);
					noteRepository.save(noteSaved);
					return new Response(200, "Note  pined", null);
				}
			} else {
				throw new NoteException("Note Id  not present");
			}
		} else {
			throw new UserException("User Id  not present");
		}
	}

	@Override
	public Response trashAndUnTrash(String tocken, String noteId) {
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Note> notes = noteRepository.findByNoteId(noteId);
			if (notes.isPresent()) {
				Note noteSaved = notes.get();
				if (noteSaved.isTrash()) {
					noteSaved.setTrash(false);
					noteRepository.save(noteSaved);
					return new Response(200, " Notes  untrash", null);
				} else {
					noteSaved.setTrash(true);
					noteRepository.save(noteSaved);
					return new Response(200, "Note Trash", null);
				}
			} else {
				throw new NoteException(" Note id not present");
			}
		} else {
			throw new UserException("User id not Present");
		}
	}

	@Override
	public List<NoteDto> getAllNotes(String tocken) {
		String id = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(id);
		if (isUserId.isPresent()) {
			List<Note> notes = noteRepository.findAll();
			List<NoteDto> listNotes = new ArrayList<>();
			for (Note userNotes : notes) {
				NoteDto noteDto = modelMapper.map(userNotes, NoteDto.class);
			}
			return listNotes;
		}
		return null;

	}

	/** @Override
	 * public Response reStoreNote(String tocken, String noteId){
	 * 
	 * String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Note> notes = noteRepository.findById(noteId);
			if (notes.isPresent()) {
				Note noteSaved = notes.get();
				if (noteSaved.istrace=true)) {

				noteSaved.add()
				
				}
	 * 
	 * 
	 * @Override public List<NoteDto> getArchiveNote(String tocken) { String id =
	 *           tockenGenerator.verifyTocken(tocken); List<Note> note =
	 *           (List<Note>) noteRepository.findById(id); List<NoteDto> listNotes =
	 *           new ArrayList<>(); for (Note userNotes : note){ NoteDto noteDto =
	 *           modelMapper.map(userNotes, NoteDto.class); if(userNotes.isArchive()
	 *           == true) { listNotes.add(noteDto); } } return listNotes; }
	 * 
	 * @Override public List<NoteDto> getTrashNote(String tocken) {
	 * 
	 *           String id = tockenGenerator.verifyTocken(tocken); List<Note> note =
	 *           (List<Note>) noteRepository.findById(id); List<NoteDto> listNotes =
	 *           new ArrayList<>(); for (Note userNotes : note) { NoteDto noteDto =
	 *           modelMapper.map(userNotes, NoteDto.class); if(userNotes.isTrash())
	 *           { listNotes.add(noteDto); } } return listNotes; }
	 * 
	 * @Override public Response deleteNoteParmament(String tocken) {
	 * 
	 * 
	 * 
	 *           }
	 **/
	@Override
	public Response addLavelToNote(String tocken, String noteId, String lavelId) {

		String id = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(id);
		if (isUserId.isPresent()) {
			Optional<Note> note = noteRepository.findByNoteId(noteId);
			if (note.isPresent()) {
				Optional<Label> level = levelRepository.findByLavelId(lavelId);
				if (level.isPresent()) {
					Note noteSaved = note.get();
					Label lavelSaved = level.get();
					List<Label> levelList = noteSaved.getLavelList();
					levelList.add(lavelSaved);
					noteSaved.setLavelList(levelList);
					noteSaved.setUpdatedDate(LocalDateTime.now());
					return new Response(200, "lavel added successfully", null);
				} else {
					throw new LevelException("lavel Id not Present");
				}
			} else {
				throw new NoteException("note id not Present");
			}
		} else {
			throw new UserException("User id not Present");
		}

	}

	@Override
	public Response deleteLavelToNote(String tocken, String noteId, String lavelId) {
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<User> isUserId = userRepository.findById(ide);
		if (isUserId.isPresent()) {
			Optional<Note> isNoteId = noteRepository.findByNoteId(noteId);
			if (isNoteId.isPresent()) {

				Optional<Label> isLevelId = levelRepository.findByLavelId(lavelId);
				if (isLevelId.isPresent()) {

					Note note = isNoteId.get();

					List<Label> listLevel = note.getLavelList();
					for (int i = 0; i < listLevel.size(); i++) {
						Label level = listLevel.get(i);
						if (listLevel.get(i).equals(lavelId)) {
							listLevel.remove(level);
							;
							note.setLavelList(listLevel);
							noteRepository.save(note);
							return new Response(200, "level is deleted", null);
						}
					}
				} else {
					throw new NoteException("Lavel not avialbel in this note");
				}
			} else {
				throw new NoteException("Note not avialable ");
			}
		} else {
			throw new NoteException("User not available");
		}
		return null;

	}

}
