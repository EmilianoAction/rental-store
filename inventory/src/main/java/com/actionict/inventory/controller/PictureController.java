package com.actionict.inventory.controller;

import com.actionict.inventory.entity.Picture;
import com.actionict.inventory.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/pictures")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @GetMapping
    public List<Picture> findAll() { return pictureService.findAll(); }

    @GetMapping("/files")
    public List<String> listFiles() { return pictureService.listFiles(); }

    @GetMapping("/film/{filmId}")
    public List<Picture> findByFilm(@PathVariable("filmId") Integer filmId) {
        return pictureService.findByFilm(filmId);
    }

    @PostMapping(path = "/upload", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("filmId") Integer filmId, @RequestParam("orderNumber") Integer orderNumber, @RequestParam("file") MultipartFile file) {
        Map<String, String> map = pictureService.uploadFile(filmId, orderNumber, file);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/updateOrder")
    public void updateOrder(@RequestBody Map<Integer, Integer> pictures) { pictureService.updateOrder(pictures); }

    @DeleteMapping("/{id}")
    public void deletePicture(@PathVariable Integer id) { pictureService.delete(id); }
}