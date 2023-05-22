package com.adebis.week_nine.model;

import com.adebis.week_nine.DTO.post.PostDTO;
import com.adebis.week_nine.DTO.post.PostRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String title;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> postImages= new ArrayList<>();


    @OneToMany(cascade =CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Dislike> dislikeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<PostLike> postLikeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Comment> commentList = new ArrayList<>();





    public Post(PostDTO postDTO){
        content = postDTO.getContent();
        title = postDTO.getTitle();
    }

    public Post(PostRequestDTO postRequestDTO){
        content = postRequestDTO.getContent();
        title = postRequestDTO.getTitle();
    }


}
