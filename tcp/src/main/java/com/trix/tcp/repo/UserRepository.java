package com.trix.tcp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trix.tcp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	
} 
