package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Hotel;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.HotelService;
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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class HotelController {

    private final HotelService hotelService;
    private final UserService userService;

    @Autowired
    public HotelController(HotelService hotelService, UserService userService) {
        this.hotelService = hotelService;
        this.userService = userService;
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
        return "hotel";
    }

    @GetMapping("/cities/{cityId}/hotels/{hotelId}/edit")
    public String getHotelEditPage(
            @PathVariable("cityId") City city,
            @PathVariable("hotelId") Hotel hotel,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("hotel", hotel);
        model.addAttribute("city", city);
        return "hotel_edit";
    }

    @GetMapping("/cities/{cityId}/hotels/add")
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
        return "hotel_edit";
    }

    @PostMapping("/cities/{cityId}/hotels")
    public String saveNewHotel(@AuthenticationPrincipal User user,
                               @PathVariable("cityId") Long cityId,
                               @RequestBody Hotel hotel) {
        hotelService.save(hotel);
        return String.format("redirect:/cities/%d/hotels", hotel.getCity().getId());
    }

    @PutMapping("/cities/{cityId}/hotels/{hotelId}")
    public String updateHotel(@PathVariable("cityId") Long cityId,
                              @PathVariable("hotelId") Long hotelId,
                              @RequestBody Hotel hotel) {
        hotelService.update(hotelId, hotel);
        return String.format("redirect:/cities/%d/hotels", cityId);
    }

    @DeleteMapping("/cities/{cityId}/hotels/{hotelId}")
    public String deleteHotel(@PathVariable("cityId") Long cityId,
                              @PathVariable("hotelId") Long hotelId) {
        hotelService.delete(hotelId);
        return String.format("redirect:/cities/%d/hotels", cityId);
    }
}
