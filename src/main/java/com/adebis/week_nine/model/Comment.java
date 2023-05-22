package com.adebis.week_nine.model;


import com.adebis.week_nine.DTO.comment.CommentDTO;
import com.adebis.week_nine.DTO.comment.CommentRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;



    public Comment(CommentDTO commentDTO){
        this.content = commentDTO.getContent();

    }

    public Comment(CommentRequestDTO commentRequestDTO){
        this.content = commentRequestDTO.getContent();

    }


}
