package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.repository.CityRepository;
import com.example.cityexplorer.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<City> getList() {
        log.info("Requested City list");
        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public City getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_CITY_ID.getErrorMessage());

        log.info("Requested the City with id: {}", id);
        return cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional
    @NotNull
    public City save(@NotNull City city) {
        Assert.notNull(city, ErrorMessages.NULL_CITY_OBJECT.getErrorMessage());

        log.info("Saving a new City");
        return cityRepository.save(city);
    }

    @Override
    @Transactional
    @NotNull
    public City update(@NotNull Long id, @NotNull City city) {
        Assert.notNull(id, ErrorMessages.NULL_CITY_ID.getErrorMessage());
        Assert.notNull(city, ErrorMessages.NULL_CITY_OBJECT.getErrorMessage());

        getById(id);

        log.info("Updating the City with id: {}", id);
        city.setId(id);

        return cityRepository.save(city);
    }

    @Override
    @Transactional
    @NotNull
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_CITY_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the City with id: {}", id);
        cityRepository.deleteById(id);
    }
}
