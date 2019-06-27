package com.bridgelabz.notes.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.notes.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

	Optional<Note> findByNoteId(String Id);

	List<Note> findByUserId(String Id);
}
