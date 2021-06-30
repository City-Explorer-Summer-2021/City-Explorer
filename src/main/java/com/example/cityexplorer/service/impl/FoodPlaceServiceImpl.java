package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.repository.FoodPlaceRepository;
import com.example.cityexplorer.service.FoodPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class FoodPlaceServiceImpl implements FoodPlaceService {

    private final FoodPlaceRepository foodPlaceRepository;

    public FoodPlaceServiceImpl(FoodPlaceRepository foodPlaceRepository) {
        this.foodPlaceRepository = foodPlaceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<FoodPlace> getList(@NotNull City city) {
        Assert.notNull(city, ErrorMessages.NULL_CITY_OBJECT.getErrorMessage());

        log.info("Requested Food place list for {} ({})", city.getName(), city.getId());
        return foodPlaceRepository.findAllByCity(city);
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public FoodPlace getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_FOOD_PLACE_ID.getErrorMessage());

        log.info("Requested the Food place with id: {}", id);
        return foodPlaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional
    @NotNull
    public FoodPlace save(@NotNull FoodPlace foodPlace) {
        Assert.notNull(foodPlace, ErrorMessages.NULL_FOOD_PLACE_OBJECT.getErrorMessage());

        log.info("Saving a new Food place");
        return foodPlaceRepository.save(foodPlace);
    }

    @Override
    @Transactional
    @NotNull
    public FoodPlace update(@NotNull Long id, @NotNull FoodPlace foodPlace) {
        Assert.notNull(id, ErrorMessages.NULL_FOOD_PLACE_ID.getErrorMessage());
        Assert.notNull(foodPlace, ErrorMessages.NULL_FOOD_PLACE_OBJECT.getErrorMessage());

        FoodPlace fetched = getById(id);

        log.info("Updating the Food place with id: {}", id);
        foodPlace.setFoodPlaceValuations(fetched.getFoodPlaceValuations());

        return foodPlaceRepository.save(foodPlace);
    }

    @Override
    @Transactional
    @NotNull
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_FOOD_PLACE_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the Food place with id: {}", id);
        foodPlaceRepository.deleteById(id);
    }
}
