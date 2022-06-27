package com.fis.sprint_3.service.impl;

import com.fis.sprint_3.model.Person;
import com.fis.sprint_3.repository.PersonRepo;
import com.fis.sprint_3.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepo personRepo;
    @Override
    public void create(Person person) {
        personRepo.save(person);
    }
}
