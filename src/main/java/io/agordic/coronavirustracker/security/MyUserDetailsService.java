package io.agordic.coronavirustracker.security;

import io.agordic.coronavirustracker.dto.UserDto;
import io.agordic.coronavirustracker.entities.User;
import io.agordic.coronavirustracker.repos.UserRepo;
import io.agordic.coronavirustracker.roles.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service("originalUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new MyUserDetails(user.get());
    }

    public User save(UserDto user) throws Exception {
        try {
            if(!userRepo.findUserByUsername(user.getUsername()).isPresent()) {
                User newUser = new User();
                newUser.setUsername(user.getUsername());
                newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
                newUser.setDateOfRegistration(new Date());
                newUser.setAddress(user.getAddress());
                newUser.setCity(user.getCity());
                newUser.setState(user.getState());
                newUser.setRole(UserRole.USER.getRoles());

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, 30);
                newUser.setDateOfExpiration(cal.getTime());
                log.info("Save user registration:", newUser);
                return userRepo.save(newUser);
            }else{
                throw new Exception(String.format("User already exist with %s username", user.getUsername()));
            }
        }catch (Exception ex){
            log.error("Error during save action: ", ex);
            throw new Exception("Error during save action!" + ex);
        }
    }

    public void cleanUp(){
        userRepo.deleteAll();
    }
}
