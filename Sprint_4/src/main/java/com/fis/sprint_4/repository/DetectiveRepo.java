package com.fis.sprint_4.repository;

import com.fis.sprint_4.model.Detective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectiveRepo extends JpaRepository<Detective, Long> {
    Detective findByUserName(String userName);

}
