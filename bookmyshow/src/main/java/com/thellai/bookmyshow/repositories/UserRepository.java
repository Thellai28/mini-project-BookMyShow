package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long aLong);

    User save(User user);
}
