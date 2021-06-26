package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.CityPhoto;
import com.example.cityexplorer.repository.CityPhotoRepository;
import com.example.cityexplorer.service.CityPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Slf4j
public class CityPhotoServiceImpl implements CityPhotoService {

    private final CityPhotoRepository cityPhotoRepository;

    @Autowired
    public CityPhotoServiceImpl(CityPhotoRepository cityPhotoRepository) {
        this.cityPhotoRepository = cityPhotoRepository;
    }

    @Override
    public List<CityPhoto> getList(City city) {
        Assert.notNull(city, ErrorMessages.NULL_CITY_PHOTO_OBJECT.getErrorMessage());

        log.info("Requested Attraction list for {} ({})", city.getName(), city.getId());
        return cityPhotoRepository.findAllByCity(city);
    }
}
