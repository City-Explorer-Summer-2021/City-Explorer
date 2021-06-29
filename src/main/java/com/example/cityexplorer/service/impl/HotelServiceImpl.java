package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Hotel;
import com.example.cityexplorer.repository.HotelRepository;
import com.example.cityexplorer.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<Hotel> getList(@NotNull City city) {
        Assert.notNull(city, ErrorMessages.NULL_CITY_OBJECT.getErrorMessage());

        log.info("Requested Hotel list for {} ({})", city.getName(), city.getId());
        return hotelRepository.findAllByCity(city);
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public Hotel getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_HOTEL_ID.getErrorMessage());

        log.info("Requested the Hotel with id: {}", id);
        return hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional
    @NotNull
    public Hotel save(@NotNull Hotel hotel) {
        Assert.notNull(hotel, ErrorMessages.NULL_HOTEL_OBJECT.getErrorMessage());

        log.info("Saving a new Hotel");
        return hotelRepository.save(hotel);
    }

    @Override
    @Transactional
    @NotNull
    public Hotel update(@NotNull Long id, @NotNull Hotel hotel) {
        Assert.notNull(id, ErrorMessages.NULL_HOTEL_ID.getErrorMessage());
        Assert.notNull(hotel, ErrorMessages.NULL_HOTEL_OBJECT.getErrorMessage());

        Hotel fetched = getById(id);

        log.info("Updating the Hotel with id: {}", id);
        hotel.setPhotos(fetched.getPhotos());

        return hotelRepository.save(hotel);
    }

    @Override
    @Transactional
    @NotNull
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_HOTEL_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the Hotel with id: {}", id);
        hotelRepository.deleteById(id);
    }
}
