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

import com.bridgelabz.elasticsearch.ElasticSearchService;
import com.bridgelabz.notes.dto.NoteDto;
import com.bridgelabz.notes.model.Note;
import com.bridgelabz.notes.service.NoteService;
import com.bridgelabz.response.Response;

@RestController
@RequestMapping("/noteservice")
public class NoteController {

	@Autowired
	NoteService noteService;
	
	@Autowired
	ElasticSearchService elasticSearch;
	 
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
  public List<Note>getAllNote(@RequestHeader String tocken)
  {
	List<Note>notesList = noteService.getAllNotes(tocken);
	
	return notesList;
	  
  }
   @PostMapping("/addlevel")
   public ResponseEntity<Response> addLevelToNote(@RequestHeader String tocken,@RequestParam String noteId,@RequestParam String lavelId)
   {
	 Response response = noteService.addLavelToNote(tocken, noteId, lavelId); 
	 return  new ResponseEntity<Response>(response,HttpStatus.OK);
	   
   }
   
  @GetMapping("/restore")
    public ResponseEntity<Response>reStoreNote(@RequestHeader String tocken,@RequestParam String noteId)
   {
	   Response response = noteService.reStoreNote(tocken, noteId);
	   return  new ResponseEntity<Response>(response,HttpStatus.OK);  
   }
   
    @GetMapping("/archive")
	public List<Note>  getArchiveNotes(@RequestHeader String tocken) {
		List<Note> listnotes = noteService.getArchiveNote(tocken);
		return listnotes;
	}
	
	@GetMapping("/trash")
	public List<Note>  getTrashNotes(@RequestHeader String tocken) {
		List<Note> listNotes = noteService.getTrashNote(tocken);
		return listNotes;
	}
   @GetMapping("/searchbytitle")
	public List<Note> elasticSearchByTitle(@RequestParam String getString,@RequestHeader String tocken){
	   
	  List<Note>listNote= elasticSearch.elasticSearchByTitle(getString, tocken);
	   return listNote;
	   
   }
  @GetMapping("/findnoteid")
  public Note findByNoteId(@RequestParam String noteId) {
	  
	  Note notes= elasticSearch.findByNoteId(noteId);
	  return notes;
	  
  }
   
}
