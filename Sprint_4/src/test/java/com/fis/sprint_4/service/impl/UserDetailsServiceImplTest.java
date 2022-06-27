package com.fis.sprint_4.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
@Slf4j
class UserDetailsServiceImplTest {
    @Autowired
    private UserDetailsService userDetailsService;
    @Test
    void loadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("thuthuy");
        log.info("userdetail: {}", userDetails);
    }
}