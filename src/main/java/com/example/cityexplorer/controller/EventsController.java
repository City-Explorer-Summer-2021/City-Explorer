package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Event;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.EventService;
import com.example.cityexplorer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EventsController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventsController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/cities/{cityId}/events")
    public String getList(
            @PathVariable("cityId") City city,
            @AuthenticationPrincipal User user,
            Model model) {
        List<Event> events = eventService.getList(city);
        model.addAttribute("events", events);
        model.addAttribute("city", city);
        return "events";
    }

    @GetMapping("/cities/{cityId}/events/{eventId}")
    public String getEventPage(
            @PathVariable("cityId") City city,
            @PathVariable("eventId") Event event,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("event", event);
        model.addAttribute("city", city);
        return "event";
    }
}
