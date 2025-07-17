package com.actionict.inventory.controller;

import com.actionict.inventory.entity.Film;
import com.actionict.inventory.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public List<Film> getAllFilms() { return filmService.findAll(); }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable("id") Integer id) throws Exception { return filmService.findById(id); }
}