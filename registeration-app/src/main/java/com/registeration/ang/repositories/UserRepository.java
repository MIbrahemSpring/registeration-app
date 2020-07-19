package com.registeration.ang.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.registeration.ang.entities.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

	UserEntity findByEmail(String email);

	UserEntity findByUserId(String id);
}
