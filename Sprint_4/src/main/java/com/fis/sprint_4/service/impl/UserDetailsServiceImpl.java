package com.fis.sprint_4.service.impl;

import com.fis.sprint_4.model.Detective;
import com.fis.sprint_4.repository.DetectiveRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private DetectiveRepo detectiveRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Detective detective = detectiveRepo.findByUserName(username);
        log.info("Detective account: {}", detective);
        if(null == detective) {
            log.error("Detective not found!,this {} invalid", username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        String roleName = String.valueOf(detective.getRank());
        log.info("Role name: {}", roleName);
        List<SimpleGrantedAuthority> roleList = new ArrayList<>();
        if(null != roleName) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
            roleList.add(authority);
        }
        roleList.forEach(role -> log.info("Role: {}", role));
        return new User(detective.getUserName()
                , detective.getPassword(), roleList);
    }
}
