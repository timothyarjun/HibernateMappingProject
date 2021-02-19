package com.spring.project.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.entity.User;
import com.spring.project.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	private UserService userService;
	
    @Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
    
   	
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user){
    	return ResponseEntity.ok().body(userService.registerUser(user));		
    }
    
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers(){
       	 return ResponseEntity.ok().body( userService.getAll());
    }
    
    @PostMapping("/findOne/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") long id){
    	return ResponseEntity.ok().body(userService.getOne(id));
    }
    
    @DeleteMapping("/userDelete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
    	User user = userService.getOne(id);
    	if(user != null) {
    		userService.deleteOne(user);
    		return ResponseEntity.ok().body("deleted user...");
    	}
    	else {
    		return ResponseEntity.ok().body("user not there ......");
    	}
    }
    
    
	@PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User getuser){
    	User newUser = userService.getOne(id);    	
    	if(newUser!=null) {
    		User user = new User();
    		user.setId(newUser.getId());
    		user.setFname(getuser.getFname());
    		user.setLname(getuser.getLname());
    		user.setEmail(getuser.getEmail());
    		user.setPassword(getuser.getPassword());
    		user.setPhone(getuser.getPhone());
    		user.setAge(getuser.getAge());
    		user.setDesignation(getuser.getDesignation());
    		user.setSimCard(getuser.getSimCard());    		
    		return ResponseEntity.ok().body(userService.registerUser(user));
    	}
    	else {
    		return ResponseEntity.ok().body(new User());
    	}
    }
}
