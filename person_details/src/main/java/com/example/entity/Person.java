package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="PERSON_SEQ_GEN")	
	@SequenceGenerator(name="PERSON_SEQ_GEN", sequenceName="PERSON_SEQ_GEN", allocationSize=1)
	@Column(name = "person_id")
	private int personId;
	@Column(name = "person_name")
	private String personName;
	@Column(name = "city")
	private String city;
	@Column(name="job_detail_id")
	private int jobDetailId;
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getpersonName() {
		return personName;
	}
	public int getjobDetailId() {
		return jobDetailId;
	}
	public void setjobDetailId(int jobDetailId) {
		this.jobDetailId = jobDetailId;
	}
	public void setpersonName(String personName) {
		this.personName = personName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", personName=" + personName + ", city=" + city + 
				 ", jobDetailId=" + jobDetailId + "]";
	}	
	
}
