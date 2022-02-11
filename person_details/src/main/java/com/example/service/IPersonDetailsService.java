package com.example.service;

import com.example.bean.CompleteDetails;
import com.example.entity.Person;

public interface IPersonDetailsService {
Person findByPersonId(Integer id);
Integer savePersonDetails(Person person);
Person updatePersonDetails(Integer id,Person personDetails);
CompleteDetails getCompleteDetails(Integer id);
}
