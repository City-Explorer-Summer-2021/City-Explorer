package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.dto.HotelFilterDto;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        return hotelRepository.findAllByCityOrderById(city);
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

    @Override
    @NotNull
    public HotelFilterDto newFilter(@NotNull City city) {
        Assert.notNull(city, ErrorMessages.NULL_CITY_OBJECT.getErrorMessage());

        return HotelFilterDto.builder()
                .priceFrom(new BigDecimal("0.00"))
                .priceTo(new BigDecimal("0.00"))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<Hotel> getFilteredList(@NotNull City city, @NotNull HotelFilterDto hotelFilter) {
        Assert.notNull(city, ErrorMessages.NULL_CITY_OBJECT.getErrorMessage());
        Assert.notNull(hotelFilter, ErrorMessages.NULL_HOTEL_FILTER_OBJECT.getErrorMessage());

        log.info("Requested Hotel list for {} ({}) with filter", city.getName(), city.getId());

        return hotelRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("city"), city));

            if (hotelFilter.getContent() != null && !hotelFilter.getContent().isEmpty()) {
                predicates.add(cb.or(getLikeContentGroup(hotelFilter, root, cb)));
            }

            if (hotelFilter.getHotelCategory() != null && hotelFilter.getHotelCategory().getId() > 0) {
                predicates.add(cb.equal(root.get("hotelCategory"), hotelFilter.getHotelCategory()));
            }

            if (hotelFilter.getPriceFrom() != null && hotelFilter.getPriceFrom().compareTo(BigDecimal.ZERO) > 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("priceFrom"), hotelFilter.getPriceFrom()));
            }

            if (hotelFilter.getPriceTo() != null && hotelFilter.getPriceTo().compareTo(BigDecimal.ZERO) > 0) {
                predicates.add(cb.and(getPriceToConditionGroup(hotelFilter, root, cb)));
            }

            return query
                    .where(predicates.toArray(new Predicate[predicates.size()]))
                    .orderBy(cb.asc(root.get("id")))
                    .getRestriction();
        });
    }

    private Predicate[] getLikeContentGroup(
            HotelFilterDto hotelFilter,
            Root<Hotel> root,
            CriteriaBuilder cb) {
        List<Predicate> groupOr = new ArrayList<>();
        String likeCondition = "%" + hotelFilter.getContent() + "%";
        groupOr.add(cb.like(root.get("name"), likeCondition));
        groupOr.add(cb.like(root.get("description"), likeCondition));
        groupOr.add(cb.like(root.get("address"), likeCondition));
        return groupOr.toArray(new Predicate[groupOr.size()]);
    }

    private Predicate[] getPriceToConditionGroup(
            HotelFilterDto hotelFilter,
            Root<Hotel> root,
            CriteriaBuilder cb) {
        List<Predicate> groupAnd = new ArrayList<>();
        groupAnd.add(cb.lessThanOrEqualTo(root.get("priceTo"), hotelFilter.getPriceTo()));
        groupAnd.add(cb.lessThanOrEqualTo(root.get("priceFrom"), hotelFilter.getPriceTo()));
        return groupAnd.toArray(new Predicate[groupAnd.size()]);
    }

}
