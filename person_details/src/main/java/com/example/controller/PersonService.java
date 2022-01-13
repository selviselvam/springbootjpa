package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Person;
import com.example.service.IPersonDetailsService;

@RestController
public class PersonService {
	@Autowired
	IPersonDetailsService IpersonDetailsService;
    @RequestMapping(value ="/checkConnection",method = RequestMethod.GET)
	public String checkConnection() {
		return "Connection is Alive";
	}
    
    @RequestMapping(value ="/addPersonDetails",method = RequestMethod.POST)
    public ResponseEntity<String> addPersonDetails(@RequestBody Person person){
    	Integer personId = IpersonDetailsService.savePersonDetails(person);
    	return ResponseEntity.ok().body("Person details saved is:"+personId);
    	
    }
    @RequestMapping(value ="/findPersonDetails",method = RequestMethod.GET)
    public ResponseEntity<Person> findPersonDetails(@RequestParam("personId") int personId ) {
       Person person = IpersonDetailsService.findByPersonId(personId);
        return ResponseEntity.ok(person);
    }
    @RequestMapping(value ="/updatePersonDetails",method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePersonDetails(@RequestParam("personId") int personId,@RequestBody Person person){
    	Person personUpdated = IpersonDetailsService.updatePersonDetails(personId, person);
    	return ResponseEntity.ok(personUpdated);
    	
    }
}
