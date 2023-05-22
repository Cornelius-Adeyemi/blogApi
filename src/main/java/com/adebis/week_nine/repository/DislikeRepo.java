package com.adebis.week_nine.repository;

import com.adebis.week_nine.model.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DislikeRepo extends JpaRepository<Dislike, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM dislike d WHERE d.user_id=?1 AND d.post_id=?2")
   Optional<Dislike> findbyUserIdAndPostId(Long userid, Long postId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO dislike (user_id, post_id) values (?1, ?2)", nativeQuery = true)
    Dislike save(Long userId, Long postId);

@Modifying
@Transactional
@Query(nativeQuery = true, value = "DELETE FROM dislike d where d.user_id=?1 and d.post_id=?2")
    void delete(Long userId, Long postId);
}
