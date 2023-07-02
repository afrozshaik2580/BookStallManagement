package com.afroz.BookStallManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.afroz.BookStallManagement.entities.Book;
import com.afroz.BookStallManagement.entities.User;
import com.afroz.BookStallManagement.repository.BookRepository;
import com.afroz.BookStallManagement.repository.UserRepository;

@Controller
@SessionAttributes("user")
public class BuyerController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/buyerpage")
	public String buyerpage(ModelMap model) {
		User user=(User) model.get("user");
		List<Book>books=bookRepository.findByEmail(user.getEmail());
		model.addAttribute("books",books);
		return "buyerpage";
	}
	
	
	@GetMapping("/buybooks")
	public String buybooks(ModelMap model) {
		List<User>sellers=userRepository.findAllSellers();
		model.addAttribute("sellers",sellers);
		return "buybooks";
	}
	
	@GetMapping("/buybooks/{sellerEmail}")
	public String listsellersbooks(@PathVariable String sellerEmail,ModelMap model) {
		User seller=userRepository.findByEmail(sellerEmail);
		model.put("name", seller.getName());
		List<Book>books=bookRepository.findByEmail(sellerEmail);
		model.addAttribute("books",books);
		return "listsellerbooks";
	}
	
	@GetMapping("/addbooktobuyer/{id}")
	public String addbooktobuyer(@PathVariable int id,ModelMap model) {
		User user=(User) model.getAttribute("user");
		Book book=bookRepository.findById(id);
		
		List<User>users=book.getUsers();
		users.add(user);
		
		book.setUsers(users);
		
		bookRepository.save(book);
		
		return "redirect:/buyerpage";
	}
	
	@GetMapping("/buyerpage/deletebook/{id}")
	public String deletebookfromuser(@PathVariable int id,ModelMap model) {
		User user=(User) model.getAttribute("user");
		Book book=bookRepository.findById(id);
		
		System.out.println(user);
		
		List<User>users=book.getUsers();
		System.out.println(users);
		
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getId()==user.getId()) {
				users.remove(i);
				break;
			}
		}
		
		System.out.println(users);
		book.setUsers(users);
		
		bookRepository.save(book);
		
		return "redirect:/buyerpage";
	}
}
