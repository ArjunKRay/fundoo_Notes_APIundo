package com.bridgelabz.level.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.level.model.Label;

public interface ILabelRepository extends MongoRepository<Label, String> {

	List<Label> findByUserId(String id);

	Optional<Label>findByLabelId(String id);
	
	
}
