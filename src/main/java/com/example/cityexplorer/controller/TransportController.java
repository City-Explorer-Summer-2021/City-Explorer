package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Transport;
import com.example.cityexplorer.service.CityService;
import com.example.cityexplorer.service.TransportCategoryService;
import com.example.cityexplorer.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class TransportController {

    private TransportService transportService;
    private TransportCategoryService transportCategoryService;
    private CityService cityService;

    @Autowired
    public TransportController(TransportService transportService, TransportCategoryService transportCategoryService, CityService cityService) {
        this.transportService = transportService;
        this.transportCategoryService = transportCategoryService;
        this.cityService = cityService;
    }

    @GetMapping("/cities/{cityId}/transports/list")
    public String openTransportPage(@PathVariable("cityId") City city,
                                    Model model) {
        List<Transport> transports = transportService.getList(city);
        model.addAttribute("transports", transports);
        model.addAttribute("city", city);

        return "transport_list";
    }

    @GetMapping("/cities/{cityId}/transports/{transportId}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getTransportEditPage(
            @PathVariable("cityId") City city,
            @PathVariable("transportId") Transport transport,
            Model model) {

        model.addAttribute("transportEdit", transport);
        model.addAttribute("city", city);
        model.addAttribute("isNew", false);
        model.addAttribute("categories", transportCategoryService.getList());
        return "transport_edit";
    }

    @GetMapping("/cities/{cityId}/transports/{transportId}/delete")
    @PreAuthorize("hasAuthority('ADNIN')")
    public String getTransportDeletePage(
            @PathVariable("cityId") City city,
            @PathVariable("transportId") Transport transport,
            Model model) {
        List<Transport> transports = transportService.getList(city);

        model.addAttribute("transports", transports);
        model.addAttribute("transport", transport);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", true);
        return "transport_list";
    }

    @GetMapping("/cities/{cityId}/transports/add")
    @PreAuthorize("hasAuthority('ADNIN')")
    public String getTransportAddPage(
            @PathVariable("cityId") City city,
            Model model) {
        Transport transport = new Transport();
        transport.setCity(city);

        model.addAttribute("transportEdit", new Transport());
        model.addAttribute("city", city);
        model.addAttribute("isNew", true);
        model.addAttribute("categories", transportCategoryService.getList());
        return "transport_edit";
    }

    @PostMapping("/cities/{cityId}/transports")
    @PreAuthorize("hasAuthority('ADNIN')")
    public String saveNewTransport(@PathVariable("cityId") City city,
                               Transport transport,
                               Model model) {
        transport.setCity(city);
        transportService.save(transport);
        model.addAttribute("city", city);
        List<Transport> transports = transportService.getList(city);
        model.addAttribute("transports", transports);

        return "transport_list";
    }

    @PutMapping(value = "/cities/{cityId}/transports/{transportId}")
    @PreAuthorize("hasAuthority('ADNIN')")
    public String updateTransport(@PathVariable("cityId") Long cityId,
                                  @PathVariable("transportId") Long transportId,
                                  Transport transport,
                                  Model model) {
        transportService.update(transportId, transport);
        City city = cityService.getById(cityId);
        List<Transport> transports = transportService.getList(city);
        model.addAttribute("city", city);
        model.addAttribute("transports", transports);

        return "transport_list";
    }

    @DeleteMapping("/cities/{cityId}/transports/{transportId}")
    @PreAuthorize("hasAuthority('ADNIN')")
    public String deleteTransport(@PathVariable("cityId") Long cityId,
                                  @PathVariable("transportId") Long transportId,
                                  Model model) {
        transportService.delete(transportId);
        City city = cityService.getById(cityId);
        List<Transport> transports = transportService.getList(city);
        model.addAttribute("city", city);
        model.addAttribute("transports", transports);

        return "transport_list";
    }
}
