package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final CityService cityService;

    @Autowired
    public GlobalControllerAdvice(CityService cityService) {
        this.cityService = cityService;
    }

        @ModelAttribute
        public void addCityInfoToAllPages(Model model) {
            City city = cityService.getById(1L);
            model.addAttribute("city", city);
        }
}
