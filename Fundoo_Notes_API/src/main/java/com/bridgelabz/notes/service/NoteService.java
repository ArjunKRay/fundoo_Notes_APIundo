package com.bridgelabz.notes.service;


import java.util.List;

import com.bridgelabz.notes.dto.NoteDto;
import com.bridgelabz.response.Response;

public interface NoteService {
	
    Response creatNote(NoteDto noteDto,String tocken);
    Response deleteNote(String tocken,String userId );
    Response updateNote(NoteDto noteDto,String tocken ,String Id);
    List<NoteDto> getAllNotes(String tocken);
    Response archiveAndUnArchive(String tocken ,String noteId);
    Response pinAndUnpPin(String tocken,String noteId);
    Response traceAndUnTrace(String tocken ,String noteId);
    List<NoteDto>getArchiveNote(String tocken );
    List<NoteDto>getTraceNote(String tocken);

}
