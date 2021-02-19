package com.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.entity.SimCard;
import com.spring.project.repository.SimRepo;
import com.spring.project.repository.UserRepo;

@Service
public class SimCardService {
	private UserRepo userRepo;
	private SimRepo simRepo;	
	
	@Autowired
	public SimCardService(SimRepo simRepo, UserRepo userRepo) {
		super();
		this.simRepo = simRepo;
		this.userRepo = userRepo;
	}

	public SimCard simRegister(SimCard sim) {
		sim.getUser().getSimCard();
		return simRepo.save(sim);
	}
	
	public List<SimCard> getSims(){
		return simRepo.findAll();
	}
	
	
}
