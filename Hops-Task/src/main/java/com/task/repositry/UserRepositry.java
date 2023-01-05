package com.task.repositry;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.task.model.User;

@Repository
public interface UserRepositry extends MongoRepository<User, String> {

	User findByEmailAndPassword(String email, String password);

	User findByotp(int otp);

	User findByEmail(String email);

	


}
