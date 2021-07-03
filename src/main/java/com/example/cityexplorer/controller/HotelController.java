package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Hotel;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.HotelCategoryService;
import com.example.cityexplorer.service.HotelService;
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
public class HotelController {

    private final HotelService hotelService;
    private final HotelCategoryService hotelCategoryService;

    @Autowired
    public HotelController(HotelService hotelService,
                           HotelCategoryService hotelCategoryService) {
        this.hotelService = hotelService;
        this.hotelCategoryService = hotelCategoryService;
    }

    @GetMapping("/cities/{cityId}/hotels")
    public String getList(
            @PathVariable("cityId") City city,
            @AuthenticationPrincipal User user,
            Model model) {
        List<Hotel> hotels = hotelService.getList(city);
        model.addAttribute("currentUser", user);
        model.addAttribute("hotels", hotels);
        model.addAttribute("city", city);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "hotels";
    }

    @GetMapping("/cities/{cityId}/hotels/{hotelId}")
    public String getHotelPage(
            @PathVariable("cityId") City city,
            @PathVariable("hotelId") Hotel hotel,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("hotel", hotel);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", false);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "hotel";
    }

    @GetMapping("/cities/{cityId}/hotels/{hotelId}/edit")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getHotelEditPage(
            @PathVariable("cityId") City city,
            @PathVariable("hotelId") Hotel hotel,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("hotel", hotel);
        model.addAttribute("city", city);
        model.addAttribute("isNew", false);
        model.addAttribute("categories", hotelCategoryService.getList());
        return "hotel_edit";
    }

    @GetMapping("/cities/{cityId}/hotels/add")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getHotelAddPage(
            @PathVariable("cityId") City city,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("currentUser", user);
        Hotel hotel = new Hotel();
        hotel.setCity(city);
        hotel.setUser(user);
        model.addAttribute("hotel", hotel);
        model.addAttribute("city", city);
        model.addAttribute("isNew", true);
        model.addAttribute("categories", hotelCategoryService.getList());
        return "hotel_edit";
    }

    @GetMapping("/cities/{cityId}/hotels/{hotelId}/delete")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getHotelDeletePage(
            @PathVariable("cityId") City city,
            @PathVariable("hotelId") Hotel hotel,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("hotel", hotel);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", true);
        model.addAttribute("isAdmin", user != null && user.isAdmin());
        return "hotel";
    }

    @PostMapping("/cities/{cityId}/hotels")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String saveNewHotel(@PathVariable("cityId") Long cityId,
                               Hotel hotel) {
        hotelService.save(hotel);
        return String.format("redirect:/cities/%d/hotels", cityId);
    }

    @PutMapping(value = "/cities/{cityId}/hotels/{hotelId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateHotel(@PathVariable("cityId") Long cityId,
                              @PathVariable("hotelId") Long hotelId,
                              Hotel hotel) {
        hotelService.update(hotelId, hotel);
        return String.format("redirect:/cities/%d/hotels", cityId);
    }

    @DeleteMapping("/cities/{cityId}/hotels/{hotelId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String deleteHotel(@PathVariable("cityId") Long cityId,
                              @PathVariable("hotelId") Long hotelId) {
        hotelService.delete(hotelId);
        return String.format("redirect:/cities/%d/hotels", cityId);
    }
}
