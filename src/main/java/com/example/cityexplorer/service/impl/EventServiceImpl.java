package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Event;
import com.example.cityexplorer.repository.EventRepository;
import com.example.cityexplorer.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<Event> getList(@NotNull City city) {
        Assert.notNull(city, ErrorMessages.NULL_CITY_OBJECT.getErrorMessage());

        log.info("Requested Event list for {} ({})", city.getName(), city.getId());
        return eventRepository.findAllByCity(city);
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public Event getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_EVENT_ID.getErrorMessage());

        log.info("Requested the Event with id: {}", id);
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional
    @NotNull
    public Event save(@NotNull Event event) {
        Assert.notNull(event, ErrorMessages.NULL_EVENT_OBJECT.getErrorMessage());

        log.info("Saving a new Event");
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    @NotNull
    public Event update(@NotNull Long id, @NotNull Event event) {
        Assert.notNull(id, ErrorMessages.NULL_EVENT_ID.getErrorMessage());
        Assert.notNull(event, ErrorMessages.NULL_EVENT_OBJECT.getErrorMessage());

        getById(id);

        log.info("Updating the Event with id: {}", id);
        event.setId(id);

        return eventRepository.save(event);
    }

    @Override
    @Transactional
    @NotNull
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_EVENT_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the Event with id: {}", id);
        eventRepository.deleteById(id);
    }
}
