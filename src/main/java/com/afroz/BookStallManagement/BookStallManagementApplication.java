package com.afroz.BookStallManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.afroz.BookStallManagement.entities.User;
import com.afroz.BookStallManagement.repository.BookRepository;
import com.afroz.BookStallManagement.repository.UserRepository;

@SpringBootApplication
public class BookStallManagementApplication implements CommandLineRunner{

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookStallManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		bookRepository.save(new Book("python", "aadil"));
//		bookRepository.save(new Book("java", "baburao"));
//		bookRepository.save(new Book("c++", "balu"));
//		bookRepository.save(new Book("Django", "kiran"));
		
//		userRepository.save(new User("afroz", "afroz@gmail.com", "pass", "buyer"));
//		userRepository.save(new User("aadil", "aadil@gmail.com", "pass", "seller"));
//		userRepository.save(new User("bhargav", "bhargav@gmail.com", "pass", "seller"));
//		userRepository.save(new User("vatsav", "vatsav@gmail.com", "pass", "seller"));

	}

}
