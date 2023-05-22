package com.adebis.week_nine.repository;

import com.adebis.week_nine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long > {

    Optional<User> findUserByEmailAndAndPassword(String email, String password);

    Optional<User> findUserByEmail(String email);

}
