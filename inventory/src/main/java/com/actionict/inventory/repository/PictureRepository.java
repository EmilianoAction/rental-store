package com.actionict.inventory.repository;

import com.actionict.inventory.entity.Film;
import com.actionict.inventory.entity.Picture;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findByFilm(Film film, Sort sort);
}