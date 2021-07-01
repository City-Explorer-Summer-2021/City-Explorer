package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.FoodPlace;
import com.example.cityexplorer.model.FoodPlaceValuation;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.repository.FoodPlaceValuationRepository;
import com.example.cityexplorer.service.FoodPlaceValuationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class FoodPlaceValuationServiceImpl implements FoodPlaceValuationService {

    private final FoodPlaceValuationRepository valuationRepository;

    public FoodPlaceValuationServiceImpl(FoodPlaceValuationRepository valuationRepository) {
        this.valuationRepository = valuationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<FoodPlaceValuation> getList(@NotNull FoodPlace foodPlace) {
        Assert.notNull(foodPlace, ErrorMessages.NULL_FOOD_PLACE_OBJECT.getErrorMessage());

        log.info("Requested Food place valuation list for {} ({})", foodPlace.getName(), foodPlace.getId());
        return valuationRepository.findAllByFoodPlace(foodPlace);
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public FoodPlaceValuation getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_FOOD_PLACE_VALUATION_ID.getErrorMessage());

        log.info("Requested the Food place valuation with id: {}", id);
        return valuationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public FoodPlaceValuation getByByFoodPlaceAndUser(@NotNull FoodPlace foodPlace, @NotNull User user) {
        Assert.notNull(foodPlace, ErrorMessages.NULL_FOOD_PLACE_OBJECT.getErrorMessage());

        if (user == null) {
            return FoodPlaceValuation.builder()
                    .foodPlace(foodPlace)
                    .value(0)
                    .build();
        }

        log.info("Requested the Food place valuation for food place with id: {} and user with id{}",
                foodPlace.getId(), user.getId());

        return valuationRepository.findByFoodPlaceAndUser(foodPlace, user)
                .orElse(FoodPlaceValuation.builder()
                        .foodPlace(foodPlace)
                        .user(user)
                        .value(0)
                        .build());
    }

    @Override
    public long getCountByFoodPlace(FoodPlace foodPlace) {
        Assert.notNull(foodPlace, ErrorMessages.NULL_FOOD_PLACE_OBJECT.getErrorMessage());

        log.info("Requested count of Food place valuation for food place with id: {}",
                foodPlace.getId());

        return valuationRepository.countByFoodPlace(foodPlace).orElse(0L);
    }

    @Override
    public double getAvgValuationByFoodPlaseId(Long id) {
        Assert.notNull(id, ErrorMessages.NULL_FOOD_PLACE_VALUATION_ID.getErrorMessage());

        log.info("Requested count of Food place valuation for food place with id: {}", id);

        return valuationRepository.getAvgValuationByFoodPlaseId(id).orElse(0.);
    }

    @Override
    @Transactional
    @NotNull
    public FoodPlaceValuation save(@NotNull FoodPlaceValuation valuation) {
        Assert.notNull(valuation, ErrorMessages.NULL_FOOD_PLACE_VALUATION_OBJECT.getErrorMessage());

        log.info("Saving a new Food place valuation");
        return valuationRepository.save(valuation);
    }

    @Override
    @Transactional
    public FoodPlaceValuation update(@NotNull Long id, @NotNull FoodPlaceValuation valuation) {
        Assert.notNull(id, ErrorMessages.NULL_FOOD_PLACE_VALUATION_ID.getErrorMessage());
        Assert.notNull(valuation, ErrorMessages.NULL_FOOD_PLACE_VALUATION_OBJECT.getErrorMessage());

        getById(id);

        log.info("Updating the Food place valuation with id: {}", id);
        valuation.setId(id);

        return valuationRepository.save(valuation);
    }

    @Override
    @Transactional
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_FOOD_PLACE_VALUATION_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the Food place valuation with id: {}", id);
        valuationRepository.deleteById(id);
    }
}
