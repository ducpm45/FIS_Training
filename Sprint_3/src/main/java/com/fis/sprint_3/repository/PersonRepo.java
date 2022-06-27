package com.fis.sprint_3.repository;

import com.fis.sprint_3.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

}
