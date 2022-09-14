package com.scc.accountservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.scc.accountservice.model.User;
import com.scc.accountservice.service.UserService;

import java.util.List;

@Controller
public class WebController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userService.saveUser(user);		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userService.getAllUsers();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/editUser/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "update-user";
	}
	
	@PostMapping("/updateUser/{id}")
	public String updateUser(@PathVariable Long id, User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	        
	    userService.saveUser(user);
	    return "redirect:/";
	}
}
