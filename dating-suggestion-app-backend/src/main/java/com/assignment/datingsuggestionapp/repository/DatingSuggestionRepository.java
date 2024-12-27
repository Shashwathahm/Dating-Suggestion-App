package com.assignment.datingsuggestionapp.repository;

import com.assignment.datingsuggestionapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatingSuggestionRepository extends MongoRepository<User, String> {
}
