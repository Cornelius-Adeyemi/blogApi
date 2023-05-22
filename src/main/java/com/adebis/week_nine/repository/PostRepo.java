package com.adebis.week_nine.repository;

import com.adebis.week_nine.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    @Override
  List<Post> findAll(Sort sort);


    List<Post> findPostsByTitleContainingOrderByCreatedTimeDesc(String words);
    List<Post> findPostsByTitleContainingIgnoreCaseOrderByCreatedTimeDesc(String words);

}
