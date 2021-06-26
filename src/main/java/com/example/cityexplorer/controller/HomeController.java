package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CityService cityService;

    @Autowired
    public HomeController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String openMainPageAndLoadDefaultCity(Model model){
        return "home";
    }

    @GetMapping("/login")
    public String openLoginPage(){
        return "login";
    }
}
