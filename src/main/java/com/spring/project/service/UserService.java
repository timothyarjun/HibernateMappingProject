package com.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.entity.User;
import com.spring.project.repository.SimRepo;
import com.spring.project.repository.UserRepo;

@Service
public class UserService {
	private UserRepo userRepo;
	private SimRepo SimRepo;
	
	@Autowired
	public UserService(UserRepo userRepo, SimRepo SimRepo) {
		super();
		this.userRepo = userRepo;
		this.SimRepo = SimRepo;
	}
	// Register user
	public User registerUser(User user) {
		user.getSimCard().getUser();
		return userRepo.save(user);
	}
	
	// select all 
	public List<User> getAll(){
		return userRepo.findAll();
	}
	
	// get one user
	public User getOne(long id) {
		return userRepo.getOne(id);
	}
	
	// delete one user
	public void deleteOne(User user) {
		userRepo.delete(user);
	}
	
	
}
