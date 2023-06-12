package com.adebis.week_nine.repository;

import com.adebis.week_nine.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {


    Optional<Token> findByValue(String token);

    @Query(value = "SELECT t FROM Token t inner join User u on t.user.id = u.id where (u.id=?1 and t.isInvalid=false)" )
    List<Token> findAllByUser_Email(Long userId);
}
