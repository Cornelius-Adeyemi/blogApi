package com.adebis.week_nine.repository;

import com.adebis.week_nine.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PostLikeRepo extends JpaRepository<PostLike, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM post_like d WHERE d.user_id=?1 AND d.post_id=?2")
    Optional<PostLike> findbyUserIdAndPostId(Long userid, Long postId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO post_like( user_id, post_id ) VALUES(?1, ?2)", nativeQuery = true)
    PostLike save(Long userId, Long postId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM post_like p  where p.user_id=?1 and p.post_id=?2", nativeQuery = true)
    void delete(Long user_id, Long post_id );
}
