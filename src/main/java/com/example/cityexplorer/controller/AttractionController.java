package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.Attraction;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.AttractionService;
import com.example.cityexplorer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AttractionController {

    private final AttractionService attractionService;
    private final UserService userService;

    @Autowired
    public AttractionController(AttractionService attractionService, UserService userService) {
        this.attractionService = attractionService;
        this.userService = userService;
    }

    @GetMapping("/cities/{cityId}/attractions")
    public String getList(
            @PathVariable("cityId") City city,
            @AuthenticationPrincipal User user,
            Model model) {
        List<Attraction> attractions = attractionService.getList(city);
        model.addAttribute("attractions", attractions);
        model.addAttribute("city", city);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "attractions";
    }

    @GetMapping("/cities/{cityId}/attractions/{attractionId}")
    public String getAttractionPage(
            @PathVariable("cityId") City city,
            @PathVariable("attractionId") Attraction attraction,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("attraction", attraction);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", false);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "attraction";
    }

    @GetMapping("/cities/{cityId}/attractions/{attractionId}/edit")
    public String getAttractionEditPage(
            @PathVariable("cityId") City city,
            @PathVariable("attractionId") Attraction attraction,
            Model model) {
        model.addAttribute("attraction", attraction);
        model.addAttribute("city", city);
        model.addAttribute("isNew", false);
        return "attraction_edit";
    }

    @GetMapping("/cities/{cityId}/attractions/add")
    public String getAttractionAddPage(
            @PathVariable("cityId") City city,
            Model model) {
        Attraction attraction = new Attraction();
        attraction.setCity(city);
        model.addAttribute("attraction", attraction);
        model.addAttribute("city", city);
        model.addAttribute("isNew", true);
        return "attraction_edit";
    }

    @GetMapping("/cities/{cityId}/attractions/{attractionId}/delete")
    public String getAttractionDeletePage(
            @PathVariable("cityId") City city,
            @PathVariable("attractionId") Attraction attraction,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("attraction", attraction);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", true);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "attraction";
    }

    @PostMapping("/cities/{cityId}/attractions")
    public String saveNewAttraction(@PathVariable("cityId") Long cityId,
                               Attraction attraction) {
        attractionService.save(attraction);
        return String.format("redirect:/cities/%d/attractions", cityId);
    }

    @PutMapping(value = "/cities/{cityId}/attractions/{attractionId}")
    public String updateAttraction(@PathVariable("cityId") Long cityId,
                              @PathVariable("attractionId") Long attractionId,
                              Attraction attraction) {
        attractionService.update(attractionId, attraction);
        return String.format("redirect:/cities/%d/attractions", cityId);
    }

    @DeleteMapping("/cities/{cityId}/attractions/{attractionId}")
    public String deleteAttraction(@PathVariable("cityId") Long cityId,
                              @PathVariable("attractionId") Long attractionId) {
        attractionService.delete(attractionId);
        return String.format("redirect:/cities/%d/attractions", cityId);
    }
}
