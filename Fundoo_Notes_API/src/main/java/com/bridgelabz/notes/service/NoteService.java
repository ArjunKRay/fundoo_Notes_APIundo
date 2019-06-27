package com.bridgelabz.notes.service;

import java.util.List;

import com.bridgelabz.notes.dto.NoteDto;
import com.bridgelabz.response.Response;

public interface NoteService {

	Response createNote(NoteDto noteDto, String tocken);

	Response deleteNote(NoteDto notedto, String tocken, String noteId);

	Response updateNote(NoteDto noteDto, String tocken, String noteId);

	List<NoteDto> getAllNotes(String tocken);

	Response archiveAndUnArchive(String tocken, String noteId);

	Response pinAndUnpPin(String tocken, String noteId);

	Response trashAndUnTrash(String tocken, String noteId);

	Response addLevelToNote(String tocken, String noteId, String levelId);

	Response deleteLevelToNote(String tocken, String noteId, String levelId);
	/*
	 * Response setReminder(String tocken,String noteId); Response
	 * removeReminder(String tocken,String noteId); Response
	 * deleteNoteParmament(String tocken); List<NoteDto> getTrashNote(String
	 * tocken); List<NoteDto> getArchiveNote(String tocken);
	 */
}
