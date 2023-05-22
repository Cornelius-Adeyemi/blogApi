package com.adebis.week_nine.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dislike")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDislike=false;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
