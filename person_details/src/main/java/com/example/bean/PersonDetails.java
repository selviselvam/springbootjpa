package com.example.bean;

import org.springframework.stereotype.Component;

@Component
public class PersonDetails {
private int personId;
private String personName;
private String city;
private int jobDetailId;
private String jobType;
private String location;
public int getPersonId() {
	return personId;
}
public void setPersonId(int personId) {
	this.personId = personId;
}
public String getPersonName() {
	return personName;
}
public void setPersonName(String personName) {
	this.personName = personName;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public int getJobDetailId() {
	return jobDetailId;
}
public void setJobDetailId(int jobDetailId) {
	this.jobDetailId = jobDetailId;
}

}
