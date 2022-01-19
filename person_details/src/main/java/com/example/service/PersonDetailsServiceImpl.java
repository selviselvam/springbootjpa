package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Person;
import com.example.repository.PersonRepository;
@Service
public class PersonDetailsServiceImpl implements IPersonDetailsService {
    @Autowired 
    PersonRepository personRepository;

	@Override
	public Person findByPersonId(Integer id) {
		int person_id = id.intValue();
		Person person = personRepository.findByPersonId(person_id);
		return person;
	}

	@Override
	public Integer savePersonDetails(Person person) {
	int personId =	personRepository.save(person).getPersonId();
	Integer iInteger = Integer.valueOf(personId);
		return iInteger;
	}

	@Override
	public Person updatePersonDetails(Integer id,Person personDetails) {
		int person_id = id.intValue();
		Person person = personRepository.findByPersonId(person_id);
		       if(personDetails.getCity() != null && personDetails.getCity().length() >0)
		       person.setCity(personDetails.getCity());  
		       if(personDetails.getpersonName() != null && personDetails.getpersonName().length() >0 )
		    	   person.setpersonName(personDetails.getpersonName());   
		      if( personDetails.getjobDetailId() > 0)
		    	  person.setjobDetailId(personDetails.getjobDetailId());
		       Person personnew =	personRepository.save(person);		    		  		   
		       
		return personnew;
	}
	
	

} 
