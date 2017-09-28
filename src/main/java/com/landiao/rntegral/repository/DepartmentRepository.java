package com.landiao.rntegral.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landiao.rntegral.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
