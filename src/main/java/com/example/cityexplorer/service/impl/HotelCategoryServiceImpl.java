package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.HotelCategory;
import com.example.cityexplorer.repository.HotelCategoryRepository;
import com.example.cityexplorer.service.HotelCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class HotelCategoryServiceImpl implements HotelCategoryService {

    private final HotelCategoryRepository hotelCategoryRepository;

    @Autowired
    public HotelCategoryServiceImpl(HotelCategoryRepository hotelCategoryRepository) {
        this.hotelCategoryRepository = hotelCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<HotelCategory> getList() {
        log.info("Requested Hotel category list");
        return hotelCategoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public HotelCategory getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_HOTEL_CATEGORY_ID.getErrorMessage());

        log.info("Requested the Hotel category with id: {}", id);
        return hotelCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional
    @NotNull
    public HotelCategory save(@NotNull HotelCategory category) {
        Assert.notNull(category, ErrorMessages.NULL_HOTEL_CATEGORY_ID.getErrorMessage());

        log.info("Saving a new Hotel category");
        return hotelCategoryRepository.save(category);
    }

    @Override
    @Transactional
    @NotNull
    public HotelCategory update(@NotNull Long id, @NotNull HotelCategory category) {
        Assert.notNull(id, ErrorMessages.NULL_HOTEL_CATEGORY_ID.getErrorMessage());
        Assert.notNull(category, ErrorMessages.NULL_HOTEL_CATEGORY_OBJECT.getErrorMessage());

        getById(id);

        log.info("Updating the Hotel category with id: {}", id);
        category.setId(id);

        return hotelCategoryRepository.save(category);
    }

    @Override
    @Transactional
    @NotNull
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_HOTEL_CATEGORY_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the Hotel category with id: {}", id);
        hotelCategoryRepository.deleteById(id);
    }
}
