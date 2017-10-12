package com.landiao.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landiao.security.entity.SystemResourceRole;

@Repository
public interface SystemResourceRoleRepository extends CrudRepository<SystemResourceRole, Integer>  {

}
