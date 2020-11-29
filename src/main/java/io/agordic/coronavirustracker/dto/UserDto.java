package io.agordic.coronavirustracker.dto;


import io.agordic.coronavirustracker.roles.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto implements Serializable {
    @NonNull
    private String username;
    @NonNull
    private String address;
    @NonNull
    private String city;
    @NonNull
    private String state;
    private Date dateOfExpiration;
    private Date dateOfRegistration;
    @NonNull
    private String password;
    private String role;
}
