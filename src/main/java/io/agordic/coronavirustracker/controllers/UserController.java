package io.agordic.coronavirustracker.controllers;

import io.agordic.coronavirustracker.dto.UserDto;
import io.agordic.coronavirustracker.entities.User;
import io.agordic.coronavirustracker.security.MyUserDetailsService;
import io.agordic.coronavirustracker.services.UserServiceConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private MyUserDetailsService userRepo;
    @Autowired
    private UserServiceConverter userServiceConverter;

//    @GetMapping("/{username}")
//    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
//        Optional<User> userByUsername = userRepo.findUserByUsername(username);
//        if(userByUsername.isPresent()){
//            UserDto tmp = userServiceConverter.convertModelToDto(userByUsername.get());
//            return new ResponseEntity<UserDto>(tmp, HttpStatus.FOUND);
//        }else{
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView  createNewUser(UserDto userDto) throws Exception {
        userRepo.save(userDto);
        return new ModelAndView("redirect:http://localhost:8084/login");

    }
}
