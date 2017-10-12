package com.landiao.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landiao.security.entity.SystemResource;

@Repository
public interface SystemResourceRepository extends CrudRepository<SystemResource, Integer>{

	@Query(value="select a.* from system_resource a where a.id in (select b.resource_id from system_resource_role b where b.role_id = ?1)", nativeQuery=true)
	List<SystemResource> findAllByRoleId(Integer roleId);
	
}
