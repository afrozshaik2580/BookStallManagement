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
public class LoginController {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/signup")
	public String signuppage(ModelMap model) {
		model.addAttribute("user",new User(null, null, null, null));
		return "signup";
	}
	
	@PostMapping("/signup")
	public String adduser(User user) {
		User isAlreadyPresent=userRepository.findByEmail(user.getEmail());
		if(isAlreadyPresent!= null) {
			return "redirect:signup?userAlreadyExists";
		}
		userRepository.save(user);
		return "redirect:login?addedUser";
	}
	
	@GetMapping("/login")
	public String loginpage(){
		return "login";
	}
	
	@PostMapping("/login")
	public String gotowelcomepage(ModelMap model, String email,String password) {
		User user=userRepository.findByEmail(email);
		if(user==null) {
			return "redirect:login?usernotfound";
		}
		model.put("user", user);
		if(user.getRole().equals("buyer")) {
			return "redirect:buyerpage";
		}
		return "redirect:sellerpage";
	}
	
}
