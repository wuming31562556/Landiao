package com.landiao.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landiao.security.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByName(String name);
	
}
