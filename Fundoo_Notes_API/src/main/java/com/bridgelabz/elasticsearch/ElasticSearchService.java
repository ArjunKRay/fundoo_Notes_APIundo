package com.bridgelabz.elasticsearch;

import java.util.List;

import com.bridgelabz.notes.model.Note;

public interface ElasticSearchService {
	
    String createNote(Note note);
	String deleteNote(String noteId);
	String updateNote(Note note);
	Note findByNoteId(String noteId);
	List<Note>elasticSearchByTitle(String title,String userId);

}
