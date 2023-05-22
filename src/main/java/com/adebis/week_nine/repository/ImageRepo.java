package com.adebis.week_nine.repository;

import com.adebis.week_nine.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepo extends JpaRepository<Image, Long> {
}
