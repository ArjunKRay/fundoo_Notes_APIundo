package com.bridgelabz.notes.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.user.model.User;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.notes.dto.NoteDto;
import com.bridgelabz.notes.model.Note;
import com.bridgelabz.notes.repository.NoteRepository;
import com.bridgelabz.response.Response;
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

	@Override
	public Response creatNote(NoteDto noteDto, String tocken) {
		
		String id = tockenGenerator.verifyTocken(tocken);
		
		if(noteDto.getTittle().isEmpty() && noteDto.getDescription().isEmpty()){
		  throw new UserException("note is Empity");
		}
		
		Note note = modelMapper.map(noteDto,Note.class);    
		Optional<User>optionalUser= userRepository.findByUserId(id);
	    
		if(optionalUser.isPresent()){
	     note.setUserId(id);
	     note.setCreatedDate(LocalDateTime.now());
	     note.setUpdatedDate(LocalDateTime.now());
	     Note noteSaved = noteRepository.save(note);	
	     return new Response(200,"note created successfully",null);
	      }
		else {
			throw new UserException("note note created") ;
		   }
		
	}
		
	@Override
	public Response deleteNote(String tocken, String id) {
		
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<Note>optionalNote= noteRepository.findByIdAndUserId(ide,id);
	    
		if(optionalNote.isPresent()) {
	       Note notes = optionalNote.get();
		   notes.setTrace(true);
		   notes.setUpdatedDate(LocalDateTime.now());
		   noteRepository.save(notes);
	       return new Response(200,"Note is delete",null) ; 
		  }
	    else {
			throw new UserException("something wrong note is note deleted");
	       }
	}

	@Override
	public Response updateNote(NoteDto noteDto, String tocken, String id) {
	    String ide = tockenGenerator.verifyTocken(tocken);
		   
	       if(noteDto.getTittle().isEmpty()&& noteDto.getDescription().isEmpty()) {
			throw new UserException("note is empty");
		    }
		    Optional<Note>note= noteRepository.findByIdAndUserId(ide, id);
		
		    if(note.isPresent()) {
	 	      Note notes= note.get();
		      notes.setTittle(noteDto.getTittle());
		      notes.setDescription(noteDto.getDescription());
	          notes.setUpdatedDate(LocalDateTime.now());
		      noteRepository.save(notes);
		      return new Response(200,"Updated note successfully",null);
		      }
	       else {
		      throw new UserException("Userid note matches");
		      }	
	}

	@Override
	public List<NoteDto> getAllNotes(String tocken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response archiveAndUnArchive(String tocken, String noteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response pinAndUnpPin(String tocken, String noteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response traceAndUnTrace(String tocken, String noteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteDto> getArchiveNote(String tocken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteDto> getTraceNote(String tocken) {
		// TODO Auto-generated method stub
		return null;
	}
	
/**	@Override
	public Response archiveAndUnArchive(String tocken, String id) {
		String ide = tockenGenerator.verifyTocken(tocken);
	    Optional<Note> notes = noteRepository.findByIdAndUserId(ide,id);
		   if(notes.isPresent()) {
			Note noteSaved = notes.get();
			noteSaved.setArchive(true);
			noteRepository.save(noteSaved);
			return new Response(200,"archived notes" ,null);
		    }
		   else {
			   throw new UserException("Unarchive");
		     }
	 }

	@Override
	public Response pinAndUnpPin(String tocken, String id) {
		 String ide = tockenGenerator.verifyTocken(tocken);
		 Optional<Note>notes = noteRepository.findByIdAndUserId(ide,id);
		      if(notes.isPresent()) { 
		    	 Note noteSaved = notes.get();
		    	 noteSaved.setPin(true);
		    	 noteRepository.save(noteSaved);
		    	 return new Response(200,"Pined notes",null);
		       }
		      else {
		    	  
		    	  throw new UserException("Unpin");  
		      } 
	 }

	@Override
	public Response traceAndUnTrace(String tocken, String id) {
		String ide = tockenGenerator.verifyTocken(tocken);
		Optional<Note>notes= noteRepository.findByIdAndUserId(ide,id);
		
		    if(notes.isPresent()) { 
		    	Note noteSaved = notes.get();
			    noteSaved.setTrace(true);
			    noteRepository.save(noteSaved);
			    return new Response(200,"Traced Note",null);
		    }
		    else {
		    	
		    	throw new UserException("UnTraced");
		       }
	   }

	@Override
	public List<NoteDto> getAllNotes(String tocken) {
		String id = tockenGenerator.verifyTocken(tocken);
		List<Note> note = (List<Note>) noteRepository.findByUserId(id);
		List<NoteDto> listNotes = new ArrayList<>();
		for (Note userNotes : note) {
			NoteDto noteDto = modelMapper.map(userNotes, NoteDto.class);	
		}
		return listNotes;
	}
	
	@Override
	public List<NoteDto> getArchiveNote(String tocken) {
		String id = tockenGenerator.verifyTocken(tocken);
		List<Note> note = (List<Note>) noteRepository.findByUserId(id);
		List<NoteDto> listNotes = new ArrayList<>();
		
		for (Note userNotes : note){
			NoteDto noteDto = modelMapper.map(userNotes, NoteDto.class);
			if(userNotes.isArchive() == true) 
			{
				listNotes.add(noteDto);
			}	
		}
		return listNotes;
	}

	@Override
	public List<NoteDto> getTraceNote(String tocken) {

		String id = tockenGenerator.verifyTocken(tocken);
		List<Note> note = (List<Note>) noteRepository.findByUserId(id);
		List<NoteDto> listNotes = new ArrayList<>();
		for (Note userNotes : note) {
			NoteDto noteDto = modelMapper.map(userNotes, NoteDto.class);
			if(userNotes.isTrace()) {
				listNotes.add(noteDto);
			}	
		}
		return listNotes;
   }
   **/
   
   
}

	

