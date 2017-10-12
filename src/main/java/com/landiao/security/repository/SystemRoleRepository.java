package com.landiao.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landiao.security.entity.SystemRole;

@Repository
public interface SystemRoleRepository extends CrudRepository<SystemRole, Integer> {
	
	@Query(value="select system_role.* from system_role where system_role.user_id = ?1", nativeQuery=true)
	List<SystemRole> findByUserId(Integer Id);
	
	List<SystemRole> findAll();
}
