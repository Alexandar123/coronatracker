package io.agordic.coronavirustracker.repos;

import io.agordic.coronavirustracker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{

    Optional<User> findUserByUsername(String username);

}
