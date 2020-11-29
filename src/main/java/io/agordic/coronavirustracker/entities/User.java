package io.agordic.coronavirustracker.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "user")
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column
    private Date dateOfRegistration;
    @Column
    private String token;
    @Column
    private Date dateOfExpiration;
    @Column(nullable = false)
    private String role;


}
