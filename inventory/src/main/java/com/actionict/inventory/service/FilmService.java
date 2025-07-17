package com.actionict.inventory.service;

import com.actionict.inventory.entity.Film;
import com.actionict.inventory.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public List<Film> findAll() { return filmRepository.findAll(); }

    public Film findById(Integer id) throws Exception { return filmRepository.findById(id).orElseThrow(() -> new Exception("Film with id "+id+" not found")); }
}