package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.TransportCategory;
import com.example.cityexplorer.repository.TransportCategoryRepository;
import com.example.cityexplorer.service.TransportCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class TransportCategoryServiceImpl implements TransportCategoryService {

    private final TransportCategoryRepository transportCategoryRepository;

    @Autowired
    public TransportCategoryServiceImpl(TransportCategoryRepository transportCategoryRepository) {
        this.transportCategoryRepository = transportCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<TransportCategory> getList() {
        log.info("Requested Transport category list");
        return transportCategoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public TransportCategory getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_TRANSPORT_CATEGORY_ID.getErrorMessage());

        log.info("Requested the Transport category with id: {}", id);
        return transportCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional
    @NotNull
    public TransportCategory save(@NotNull TransportCategory category) {
        Assert.notNull(category, ErrorMessages.NULL_TRANSPORT_CATEGORY_OBJECT.getErrorMessage());

        log.info("Saving a new Transport category");
        return transportCategoryRepository.save(category);
    }

    @Override
    @Transactional
    @NotNull
    public TransportCategory update(@NotNull Long id, @NotNull TransportCategory category) {
        Assert.notNull(id, ErrorMessages.NULL_TRANSPORT_CATEGORY_ID.getErrorMessage());
        Assert.notNull(category, ErrorMessages.NULL_TRANSPORT_CATEGORY_OBJECT.getErrorMessage());

        getById(id);

        log.info("Updating the Transport category with id: {}", id);
        category.setId(id);

        return transportCategoryRepository.save(category);
    }

    @Override
    @Transactional
    @NotNull
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_TRANSPORT_CATEGORY_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the Transport category with id: {}", id);
        transportCategoryRepository.deleteById(id);
    }
}
