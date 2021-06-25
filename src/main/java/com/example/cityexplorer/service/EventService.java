package com.example.cityexplorer.service;

import com.example.cityexplorer.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getList();

    Event getById(Long id);

    Event save(Event event);

    Event update(Long id, Event event);

    void delete(Long id);
}
