package com.bridgelabz.level.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.bridgelabz.level.model.Level;

public interface ILevelRepository extends MongoRepository<Level,String> {
	
	
	List<Level> findByUserId(String userId);
	Optional<Level>findByUserIdAndLevelName(String  userId ,String levelName );
    Optional<Level>findByLevelIdAndUserId(String levelId, String UserId);
}    