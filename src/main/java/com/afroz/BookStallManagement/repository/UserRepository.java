package com.afroz.BookStallManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.afroz.BookStallManagement.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	
	@Query(value = "select * from user where role='seller';",nativeQuery = true)
	List<User> findAllSellers();
}
