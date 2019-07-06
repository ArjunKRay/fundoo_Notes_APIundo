package com.bridgelabz.notes.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.notes.model.Note;
import com.bridgelabz.user.model.User;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

	Optional<Note> findByNoteId(String Id);

	Optional<User> findByUserId(String Id);
	List<Note> findByUserIdAndArchive(String userId,boolean bool);
	List<Note>findByUserIdAndTrash(String userId ,boolean bool);
}
