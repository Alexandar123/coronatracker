package io.agordic.coronavirustracker.controllers;

import io.agordic.coronavirustracker.repos.UserRepo;
import io.agordic.coronavirustracker.security.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DatabaseDML {

    @Autowired
    private MyUserDetailsService userRepo;

    @GetMapping("/truncate")
    public void cleanUp(){
        userRepo.cleanUp();
        log.info("Cleaned up database!");
    }
}
