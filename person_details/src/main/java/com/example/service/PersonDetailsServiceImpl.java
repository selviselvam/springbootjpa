package com.example.service;

import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.bean.CompleteDetails;
import com.example.bean.JobDetails;
import com.example.bean.PersonDetails;
import com.example.entity.Person;
import com.example.repository.PersonRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
public class PersonDetailsServiceImpl implements IPersonDetailsService {
    @Autowired 
    PersonRepository personRepository;

    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    PersonDetails personDetails;
    
    @Autowired
    JobDetails jobDetails;
    
    @Autowired
    CompleteDetails completeDetails;
    
    public static final String PERSON_SERVICE = "personDetailService";
    
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
		     //  JobDetails jobDetails = restTemplate.getForObject(url: "http::JOB_DETAIL_SERVICE/jobDetails"+personDetails.getjobDetailId(), JobDetails.class) ;
		return personnew;
	}

	@CircuitBreaker(name = PERSON_SERVICE, fallbackMethod="getCallBackMethod")
	@Override
	public CompleteDetails getCompleteDetails(Integer id) {
		int person_id = id.intValue();
		Person person = personRepository.findByPersonId(person_id);
		personDetails.setPersonId(id);
		   if(person.getCity() != null && person.getCity().length() >0)
			   personDetails.setCity(person.getCity());
		   if(person.getpersonName() != null && person.getpersonName().length()>0)
			   personDetails.setPersonName(person.getpersonName());
		   if(person.getjobDetailId()>0)
			   personDetails.setJobDetailId(person.getjobDetailId());
		   int jobDetailId = person.getjobDetailId();
		  //To get JOb details of the person instead ip with host just we are calling with service name registred in eurekaserver
		   String uri = "http://JOB-DETAIL-SERVICE/jobDetails/findJobDetails";
		   UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
	                .queryParam("jobDetailId",person.getjobDetailId()).build();
			/*
			 *  way to pass query parameter and request parameter
			 * String uri = http://my-rest-url.org/rest/account/{account};
			 * 
			 * Map<String, String> uriParam = new HashMap<>(); 
			 * uriParam.put("account","my_account");
			 * 
			 * UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
			 * .queryParam("pageSize","2") .queryParam("page","0")
			 * .queryParam("name","my_name").build();
			 * 
			 * HttpEntity<String> requestEntity = new HttpEntity<>(null, getHeaders());
			 * 
			 * ResponseEntity<String> strResponse =
			 * restTemplate.exchange(builder.toUriString(),HttpMethod.GET, requestEntity,
			 * String.class,uriParam); //final URL:
			 * http://my-rest-url.org/rest/account/my_account?pageSize=2&page=0&name=my_name
			 */	
		   //HttpEntity<String> requestEntity = new HttpEntity<>(null, getHeaders());
		   System.out.println("the requested string:"+builder.toString());
		   //below one also will work uri shoulbe value untill service name alone
		  // JobDetails jobDetails = restTemplate.getForObject(uri+"/jobDetails/findJobDetails?jobDetailId={jobDetailId}", JobDetails.class,jobDetailId) ;
		   JobDetails jobDetails = restTemplate.getForObject(builder.toString(), JobDetails.class,jobDetailId) ;
		   completeDetails.setPersonDetails(personDetails);
		   completeDetails.setJobDetails(jobDetails);
		return completeDetails;
	}
			
	public CompleteDetails getCallBackMethod(Exception e) {
		JobDetails jobDetails = new JobDetails();
		jobDetails.setJobDetailId(1);
		jobDetails.setJobType("Default");
		jobDetails.setLocation("default location");
		
		PersonDetails personDetails = new PersonDetails();
		personDetails.setCity("defailt city");
		personDetails.setJobDetailId(1);
		personDetails.setPersonId(1);
		personDetails.setPersonName("default");
		 CompleteDetails completeDetails = new CompleteDetails();
		 completeDetails.setJobDetails(jobDetails);	
		 completeDetails.setPersonDetails(personDetails);
		 return completeDetails;
	}
} 
