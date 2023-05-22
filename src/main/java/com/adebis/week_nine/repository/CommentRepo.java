package com.adebis.week_nine.repository;

import com.adebis.week_nine.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment, Long> {


    Optional<Comment> findCommentByContentAndUser_Id(String content, Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE from comment c where c.id=?1")
    void deleteById(Long commentId);
}
