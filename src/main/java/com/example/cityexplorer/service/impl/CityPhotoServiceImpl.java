package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.CityPhoto;
import com.example.cityexplorer.repository.CityPhotoRepository;
import com.example.cityexplorer.service.CityPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.util.List;

@Service
@Slf4j
public class CityPhotoServiceImpl implements CityPhotoService {

    @Value("${upload.path}")
    private String uploadPath;

    private final CityPhotoRepository cityPhotoRepository;

    @Autowired
    public CityPhotoServiceImpl(CityPhotoRepository cityPhotoRepository) {
        this.cityPhotoRepository = cityPhotoRepository;
    }

    @Override
    public List<CityPhoto> getList(City city) {
        Assert.notNull(city, ErrorMessages.NULL_CITY_OBJECT.getErrorMessage());

        log.info("Requested CityPhoto list for {} ({})", city.getName(), city.getId());
        return cityPhotoRepository.findAllByCity(city);
    }

    @Override
    public void save(CityPhoto photo) {
        Assert.notNull(photo, ErrorMessages.NULL_CITY_PHOTO_OBJECT.getErrorMessage());

        log.info("Requested save CityPhoto {} ({})", photo.getName(), photo.getId());
        cityPhotoRepository.save(photo);
    }

    @Override
    public void delete(CityPhoto photo) {
        Assert.notNull(photo, ErrorMessages.NULL_CITY_PHOTO_OBJECT.getErrorMessage());

        log.info("Requested deleting CityPhoto {} ({})", photo.getName(), photo.getId());
        cityPhotoRepository.delete(photo);

        File fileForDelete = new File(uploadPath + "" + photo.getCity().getId() + "\\\\" + photo.getName());

        if (fileForDelete.exists()) {
            if (fileForDelete.delete()) {
                log.info("File was successfully deleted from {}!", fileForDelete);
            }
        }
    }

}
