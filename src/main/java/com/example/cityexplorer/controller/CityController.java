package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

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
        model.addAttribute("isNew", true);
        model.addAttribute("cityEdit", new City());
        return "city_edit";
    }

    @PostMapping
    public String addNewCityToDB(
            @Valid @ModelAttribute("cityEdit") City city,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "city_edit";
        }
        cityService.save(city);

        return "redirect:/";
    }

    @GetMapping("/list")
    public String openCitiesPage(Model model) {
        List<City> cities = cityService.getList();
        model.addAttribute("cities", cities);
        return "cities_list";
    }

    @GetMapping("/{cityId}/delete")
    public String getCityDeletePage(
            @PathVariable("cityId") City city,
            Model model) {

        List<City> cities = cityService.getList();
        model.addAttribute("cities", cities);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", true);
        return "cities_list";
    }

    @DeleteMapping("/{cityId}")
    public String deleteCity(@PathVariable("cityId") Long id) {
        cityService.delete(id);
        return "redirect:/cities/list";
    }

    @GetMapping("/{cityId}/edit")
    public String getCityEditPage(
            @PathVariable("cityId") City city,
            Model model) {
        model.addAttribute("cityEdit", city);
        model.addAttribute("isNew", false);
        return "city_edit";
    }

    @PutMapping(value = "/{cityId}")
    public String updateCity(@PathVariable("cityId") Long cityId,
                             @Valid @ModelAttribute("cityEdit") City city,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "city_edit";
        }
        cityService.update(cityId,city);
        return "redirect:/cities/list";
    }

}
