package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.dto.CityDto;
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
import java.util.LinkedHashMap;
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
    public LinkedHashMap<CityDto,String> getOrderedByNameMap() {
        log.info("Requested City list ordered by Name");
        List<City> sortedCities =  cityRepository.findByOrderByNameAsc();

        LinkedHashMap<CityDto, String> mapWhereCityAsValueFirstLetterAsKey = new LinkedHashMap<>();

        if (sortedCities.size() > 0) {
            mapWhereCityAsValueFirstLetterAsKey.put(
                    new CityDto(sortedCities.get(0).getId(), sortedCities.get(0).getName()),
                    sortedCities.get(0).getName().substring(0, 1));

            for (int i = 1; i < sortedCities.size(); i++) {
                String valueLetter = sortedCities.get(i).getName().substring(0, 1);
                if (sortedCities.get(i - 1).getName().substring(0, 1).equals(valueLetter)) {
                    valueLetter = " ";
                }
                mapWhereCityAsValueFirstLetterAsKey.put(
                        new CityDto(sortedCities.get(i).getId(), sortedCities.get(i).getName()), valueLetter);
            }
        } else {
            log.info("Cities list size is 0");
        }

        return mapWhereCityAsValueFirstLetterAsKey;
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

        City fetched = getById(id);

        log.info("Updating the City with id: {}", id);
        city.setPhotos(fetched.getPhotos());
        city.setTransports(fetched.getTransports());
        city.setHotels(fetched.getHotels());
        city.setAttractions(fetched.getAttractions());
        city.setEvents(fetched.getEvents());
        city.setFoodPlaces(fetched.getFoodPlaces());


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
