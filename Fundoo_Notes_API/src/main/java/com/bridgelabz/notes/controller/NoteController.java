package com.bridgelabz.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.notes.dto.NoteDto;
import com.bridgelabz.notes.service.NoteService;
import com.bridgelabz.response.Response;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	NoteService noteService;
	
	@PostMapping("/creat")
	public ResponseEntity<Response>creatNote(@RequestBody NoteDto noteDto,String tocken)
	{
	    Response response = noteService.creatNote(noteDto,tocken);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
  @DeleteMapping("/delete")
  public ResponseEntity<Response>noteDelete(@RequestBody String tocken,String id)
  {
	  Response response = noteService.deleteNote(tocken,id);
	  return new ResponseEntity<Response>(response, HttpStatus.OK);
  }
  
   @PutMapping("/update")
  public ResponseEntity<Response>noteUpdate(@RequestBody NoteDto noteDto, String tocken,String id){
	  Response response = noteService.updateNote(noteDto,tocken,id);
	  return new ResponseEntity <Response>(response,HttpStatus.OK);
  }
   @GetMapping("/getAll")
  public List<NoteDto>getAllNote(@RequestBody String tocken)
  {
	List<NoteDto>notesList = noteService.getAllNotes(tocken);
	
	return notesList;
	  
  }
   
   @GetMapping("/getarchivenotes")
	public List<NoteDto>  getArchiveNotes(@RequestBody String tocken) {
		List<NoteDto> listnotes = noteService.getArchiveNote(tocken);
		return listnotes;
	}
	
	@GetMapping("/gettrashnotes")
	public List<NoteDto>  getTrashNotes(@RequestBody String tocken) {
		List<NoteDto> listnotes = noteService.getTraceNote(tocken);
		return listnotes;
	}
   
   
   
}
