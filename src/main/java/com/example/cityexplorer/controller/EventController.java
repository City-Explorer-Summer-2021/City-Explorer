package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Event;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/cities/{cityId}/events")
    public String getList(
            @PathVariable("cityId") City city,
            @AuthenticationPrincipal User user,
            Model model) {
        List<Event> events = eventService.getList(city);
        model.addAttribute("events", events);
        model.addAttribute("city", city);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "events";
    }

    @GetMapping("/cities/{cityId}/events/{eventId}/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getEventEditPage(
            @PathVariable("cityId") City city,
            @PathVariable("eventId") Event event,
            Model model) {
        model.addAttribute("event", event);
        model.addAttribute("city", city);
        model.addAttribute("isNew", false);
        return "event_edit";
    }

    @GetMapping("/cities/{cityId}/events/{eventId}")
    public String getEventPage(
            @PathVariable("cityId") City city,
            @PathVariable("eventId") Event event,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("event", event);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", false);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "event";
    }

    @GetMapping("/cities/{cityId}/events/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getEventAddPage(
            @PathVariable("cityId") City city,
            Model model) {
        Event event = new Event();
        event.setCity(city);
        model.addAttribute("event", event);
        model.addAttribute("city", city);
        model.addAttribute("isNew", true);
        return "event_edit";
    }

    @GetMapping("/cities/{cityId}/events/{eventId}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getEventDeletePage(
            @PathVariable("cityId") City city,
            @PathVariable("eventId") Event event,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("event", event);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", true);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "event";
    }

    @PostMapping("/cities/{cityId}/events")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveNewEvent(@PathVariable("cityId") Long cityId,
                               Event event) {
        eventService.save(event);
        return String.format("redirect:/cities/%d/events", cityId);
    }

    @PutMapping(value = "/cities/{cityId}/events/{eventId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateEvent(@PathVariable("cityId") Long cityId,
                              @PathVariable("eventId") Long eventId,
                              Event event) {
        eventService.update(eventId, event);
        return String.format("redirect:/cities/%d/events", cityId);
    }

    @DeleteMapping("/cities/{cityId}/events/{eventId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteEvent(@PathVariable("cityId") Long cityId,
                              @PathVariable("eventId") Long eventId) {
        eventService.delete(eventId);
        return String.format("redirect:/cities/%d/events", cityId);
    }
}
