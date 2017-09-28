package com.landiao.right.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landiao.right.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByName(String name);
	
}
