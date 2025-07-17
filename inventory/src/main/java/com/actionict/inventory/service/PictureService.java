package com.actionict.inventory.service;

import com.actionict.inventory.entity.Film;
import com.actionict.inventory.entity.Picture;
import com.actionict.inventory.repository.FilmRepository;
import com.actionict.inventory.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;
    private final FilmRepository filmRepository;
    private final String absDirPath = "C:/2505 Corso/resources/pics";//new FileSystemResource(dirPath).getFile().getAbsolutePath();
    private final File dir = new File(absDirPath);

    public List<Picture> findAll() { return pictureRepository.findAll(); }

    public List<String> listFiles() {
        List<String> fileNames = new ArrayList<>();
        fileNames.add(absDirPath);
        Collection<File> files = FileUtils.listFiles(dir, new String[]{"jpg"}, false);
        for (File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    public List<Picture> findByFilm(Integer filmId) {
        List<Picture> pictures = new ArrayList<>();
        Film film = filmRepository.findById(filmId).orElseThrow();
        Sort sort = Sort.by(Sort.Direction.ASC, "orderNumber");
        pictures = pictureRepository.findByFilm(film, sort);
        return pictures;
    }

    public Map<String, String> uploadFile(Integer filmId, Integer orderNumber, MultipartFile mpFile) {
        Film film = filmRepository.findById(filmId).orElseThrow();
        Picture picture = new Picture();
        picture.setFilm(film);
        picture.setOrderNumber(orderNumber);
        pictureRepository.save(picture);
        int picId = picture.getId();
        //String fileName = "pic"+picId+".jpg";
        //https://howtodoinjava.com/spring-boot/spring-boot-file-upload-rest-api/
        Map<String, String> map = new HashMap<>();
        map.put("fileName", mpFile.getOriginalFilename());
        map.put("fileSize", String.valueOf(mpFile.getSize()));
        map.put("fileContentType", mpFile.getContentType());
        File file = getFile(picId); //new File(absDirPath+"/"+generateFileName(picId));
        try {
            file.createNewFile();
            mpFile.transferTo(file);
        } catch (IOException e) { throw new RuntimeException(e); }
        map.put("message", "File "+file.getName()+" successfully uploaded");
        return map;
    }

    public void updateOrder(Map<Integer, Integer> pictures) {
        for (Integer id : pictures.keySet()) {
            Picture picture = pictureRepository.findById(id).orElseThrow();
            picture.setOrderNumber(pictures.get(id));
            pictureRepository.save(picture);
        }
    }

    public void delete(Integer id) {
        Picture picture = pictureRepository.findById(id).orElseThrow();
        File file = getFile(id);
        try {
            FileUtils.delete(file);
        } catch (IOException e) { throw new RuntimeException(e); }
        pictureRepository.deleteById(id);
    }

    private File getFile(Integer id) { return new File(absDirPath+"/pic"+id+".jpg"); }
    //private String generateFileName(Integer id) { return "pic"+id+".jpg"; }
}