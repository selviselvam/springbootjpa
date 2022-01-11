package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Person;
import com.example.service.IPersonDetailsService;

@RestController
public class PersonService {
	@Autowired
	IPersonDetailsService IpersonDetailsService;
    @RequestMapping("/checkConnection")
	public String checkConnection() {
		return "Connection is Alive";
	}
    
    @RequestMapping("/addPersonDetails")
    public ResponseEntity<String> addPersonDetails(@RequestBody Person person){
    	Integer personId = IpersonDetailsService.savePersonDetails(person);
    	return ResponseEntity.ok().body("Person details saved is:"+personId);
    	
    }
    
}
