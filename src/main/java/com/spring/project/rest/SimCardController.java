package com.spring.project.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.entity.SimCard;
import com.spring.project.service.SimCardService;

@RestController
@RequestMapping("/sim")
public class SimCardController {
	private SimCardService simService;
	
	@Autowired
	public SimCardController(SimCardService simService) {
		super();
		this.simService = simService;
	}
	
	@PostMapping("/simRegister")
	public ResponseEntity<SimCard> simRegister(@RequestBody SimCard sim){
		return ResponseEntity.ok().body(simService.simRegister(sim));
	}
	
	@GetMapping("/getSims")
	public ResponseEntity<List<SimCard>> getSims(){
		return ResponseEntity.ok().body(simService.getSims());
	}
}
