package com.afroz.BookStallManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.afroz.BookStallManagement.entities.Book;
import com.afroz.BookStallManagement.entities.User;
import com.afroz.BookStallManagement.repository.BookRepository;
import com.afroz.BookStallManagement.repository.UserRepository;

@Controller
@SessionAttributes("user")
public class SellerController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/sellerpage")
	public String sellerpage(ModelMap model) {
		User user =(User) model.get("user");
		List<Book>books=bookRepository.findByEmail(user.getEmail());
		model.addAttribute("books",books);
		return "sellerpage";
	}
	
	@GetMapping("/sellerpage/addBookToSell")
	public String addbookfromsellerpage(ModelMap model) {
		model.addAttribute("book", new Book(null, null));
		return "addbook";
	}
	
	@PostMapping("/sellerpage/addBookToSell")
	public String addbookfromseller(Book book,ModelMap model) {
		User user= (User) model.get("user");
		
		List<User>users=book.getUsers();
		users.add(user);
		
		book.setUsers(users);
		
		bookRepository.save(book);
		return "redirect:/sellerpage";
	}
	
	@GetMapping("/sellerpage/deletebook/{id}")
	public String deleteStringbookfromseller(@PathVariable int id,ModelMap model) {
		User user=(User) model.getAttribute("user");
		Book book=bookRepository.findById(id);
		List<User>users=book.getUsers();
		
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getId()==user.getId()) {
				users.remove(i);
				break;
			}
		}
		
		book.setUsers(users);
		bookRepository.save(book);
		return "redirect:/sellerpage";
	}
	
	@GetMapping("/sellerpage/updatebook/{id}")
	public String updatebook(@PathVariable int id,ModelMap model) {
		Book book=bookRepository.findById(id);
		model.addAttribute(book);
		return "addbook";
	}
	
	@PostMapping("/sellerpage/updatebook/{id}")
	public String updatebookinrepo(@PathVariable int id,Book book) {
		Book oldBook=bookRepository.findById(id);
		oldBook.setAuthor(book.getAuthor());
		oldBook.setName(book.getName());
		bookRepository.save(oldBook);
		return "redirect:/sellerpage";
 	}
}
