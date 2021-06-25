package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.Attraction;
import com.example.cityexplorer.repository.AttractionRepository;
import com.example.cityexplorer.service.AttractionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<Attraction> getList() {
        log.info("Requested Attraction list");
        return attractionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public Attraction getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ATTRACTION_ID.getErrorMessage());

        log.info("Requested the Attraction with id: {}", id);
        return attractionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional
    @NotNull
    public Attraction save(@NotNull Attraction attraction) {
        Assert.notNull(attraction, ErrorMessages.NULL_ATTRACTION_OBJECT.getErrorMessage());

        log.info("Saving a new Attraction");
        return attractionRepository.save(attraction);
    }

    @Override
    @Transactional
    @NotNull
    public Attraction update(@NotNull Long id, @NotNull Attraction attraction) {
        Assert.notNull(id, ErrorMessages.NULL_ATTRACTION_ID.getErrorMessage());
        Assert.notNull(attraction, ErrorMessages.NULL_ATTRACTION_OBJECT.getErrorMessage());

        getById(id);

        log.info("Updating the Attracton with id: {}", id);
        attraction.setId(id);

        return attractionRepository.save(attraction);
    }

    @Override
    @Transactional
    @NotNull
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ATTRACTION_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the Attraction with id: {}", id);
        attractionRepository.deleteById(id);
    }
}
