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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return "attractions";
    }

    @GetMapping("/cities/{cityId}/attractions/{attractionId}")
    public String getHotelPage(
            @PathVariable("cityId") City city,
            @PathVariable("attractionId") Attraction attraction,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("attraction", attraction);
        model.addAttribute("city", city);
        return "attraction";
    }

}
