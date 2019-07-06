package com.bridgelabz.notes.service;

import java.util.List;

import com.bridgelabz.notes.dto.NoteDto;
import com.bridgelabz.notes.model.Note;
import com.bridgelabz.response.Response;

public interface NoteService {

	Response createNote(NoteDto noteDto, String tocken);

	Response deleteNote(NoteDto notedto, String tocken, String noteId);

	Response updateNote(NoteDto noteDto, String tocken, String noteId);

	List<Note> getAllNotes(String tocken);

	Response archiveAndUnArchive(String tocken, String noteId);

	Response pinAndUnpPin(String tocken, String noteId);

	Response trashAndUnTrash(String tocken, String noteId);

	Response addLavelToNote(String tocken, String noteId, String lavelId);

	Response deleteLavelToNote(String tocken, String noteId, String lavelId);

	Response reStoreNote(String tocken, String noteId);

	Response setReminder(String tocken, String noteId);

	Response removeReminder(String tocken, String noteId);

	List<Note> getArchiveNote(String tocken);

	List<Note> getTrashNote(String tocken);
}
