package com.adebis.week_nine.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {

    @Id
    @SequenceGenerator(name="image_sequence", sequenceName = "image_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence")
    private Long id;

    private String name;

    private String url;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;


    @Lob
    private  byte[] image;
}
