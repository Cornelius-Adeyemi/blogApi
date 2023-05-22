package com.adebis.week_nine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="post_like")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLike {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private Boolean islike= false;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "user_id")
   private User user;

}
