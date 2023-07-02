package com.afroz.BookStallManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.afroz.BookStallManagement.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query(value = "select b.id,b.name,b.author from book b join user_book ub on b.id=ub.book_id join user u on ub.user_id=u.id where u.email= ?1",
			nativeQuery = true
			)
	List<Book>findByEmail(String email);
	
	@Query(value = "select * from book where id=?1",nativeQuery = true)
	Book findById(int id);
}
