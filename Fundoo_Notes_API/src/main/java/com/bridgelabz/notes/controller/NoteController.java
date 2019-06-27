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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.notes.dto.NoteDto;
import com.bridgelabz.notes.service.NoteService;
import com.bridgelabz.response.Response;

@RestController
@RequestMapping("/noteservice")
public class NoteController {

	@Autowired
	NoteService noteService;
	 
	
	@PostMapping("/creat")
	public ResponseEntity<Response>creatNote(@RequestBody NoteDto noteDto,@RequestHeader String tocken)
	{
	    Response response = noteService.createNote(noteDto,tocken);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
  @DeleteMapping("/delete")
  public ResponseEntity<Response>noteDelete(@RequestBody NoteDto noteDto,@RequestHeader String tocken,@RequestParam  String id)
  {
	  Response response = noteService.deleteNote(noteDto,tocken,id);
	  return new ResponseEntity<Response>(response, HttpStatus.OK);
  }
  
   @PutMapping("/update")
  public ResponseEntity<Response>noteUpdate(@RequestBody NoteDto noteDto,@RequestHeader String tocken,@RequestParam String id){
	  Response response = noteService.updateNote(noteDto,tocken,id);
	  return new ResponseEntity <Response>(response,HttpStatus.OK);
  }
   @GetMapping("/getAll")
  public List<NoteDto>getAllNote(@RequestHeader String tocken)
  {
	List<NoteDto>notesList = noteService.getAllNotes(tocken);
	
	return notesList;
	  
  }
   @PostMapping("/addlevel")
   public ResponseEntity<Response> addLevelToNote(@RequestHeader String tocken,@RequestParam String noteId,@RequestParam String levelId)
   {
	 Response response = noteService.addLevelToNote(tocken, noteId, levelId); 
	 return  new ResponseEntity<Response>(response,HttpStatus.OK);
	   
   }
   
   public ResponseEntity<Response>deleteLevelToNote(@RequestHeader String tocken,@RequestParam String noteId,@RequestParam String levelId)
   {
	   Response response = noteService.deleteLevelToNote(tocken, noteId, levelId);
	   return  new ResponseEntity<Response>(response,HttpStatus.OK);  
   }
/**   @GetMapping("/archive")
	public List<NoteDto>  getArchiveNotes(@RequestHeader String tocken) {
		List<NoteDto> listnotes = noteService.getArchiveNote(tocken);
		return listnotes;
	}
	
	@GetMapping("/trash")
	public List<NoteDto>  getTrashNotes(@RequestHeader String tocken) {
		List<NoteDto> listnotes = noteService.getTrashNote(tocken);
		return listnotes;
	}
   
   **/
   
}
