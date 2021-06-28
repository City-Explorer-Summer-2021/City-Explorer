package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/add")
    public String openAddCityPage(Model model) {
        model.addAttribute("newCity", new City());
        return "add_city";
    }

    @PostMapping
    public String addNewCityToDB(
            @Valid @ModelAttribute("newCity") City city,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add_city";
        }
        cityService.save(city);

        return "redirect:/";
    }

}
