package io.agordic.coronavirustracker.services;

import io.agordic.coronavirustracker.dto.UserDto;
import io.agordic.coronavirustracker.entities.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceConverter {

    public UserDto convertModelToDto(User user){
            return new UserDto()
                    .setAddress(user.getAddress())
                    .setCity(user.getCity())
                    .setDateOfExpiration(user.getDateOfExpiration())
                    .setDateOfRegistration(user.getDateOfRegistration())
                    .setState(user.getState())
                    .setUsername(user.getUsername())
                    .setRole(user.getRole())
                    .setPassword("ENCRIPTED");
    }

    public User convertDtoToModel(UserDto userDto, String role, String token) {
        return new User()
                .setAddress(userDto.getAddress())
                .setCity(userDto.getCity())
                .setDateOfExpiration(userDto.getDateOfExpiration())
                .setDateOfRegistration(userDto.getDateOfRegistration())
                .setState(userDto.getState())
                .setUsername(userDto.getUsername())
                //.setRole(role)
                .setPassword(passwordEncoder().encode(userDto.getPassword()))
                .setToken(token);
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
