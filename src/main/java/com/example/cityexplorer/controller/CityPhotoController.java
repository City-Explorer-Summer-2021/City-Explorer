package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.CityPhoto;
import com.example.cityexplorer.service.CityPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class CityPhotoController {

    @Value("${upload.path}")
    private String uploadPath;

    private final CityPhotoService cityPhotoService;

    @Autowired
    public CityPhotoController(CityPhotoService cityPhotoService) {
        this.cityPhotoService = cityPhotoService;
    }

    @GetMapping("/cities/{cityId}/photo/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addPhotoPage(@PathVariable("cityId") City city,
                               Model model) {

        model.addAttribute("city", city);

        List<CityPhoto> cityPhotos = cityPhotoService.getList(city);
        model.addAttribute("cityPhotos", cityPhotos);

        return "add_city_photo";
    }

    @PostMapping("/cities/{cityId}/photo/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @PathVariable("cityId") City city,
            RedirectAttributes redirectAttributes,
            Model model) throws IOException {

        if (file != null && file.getBytes().length > 0) {
            File savePath = new File(uploadPath + "\\\\" + city.getId() + "\\\\");

            if (!savePath.exists()) {
                savePath.mkdirs();
            }

            String uuid = UUID.randomUUID().toString();
            String resultFileName = uuid + "." + file.getOriginalFilename();

            File finalPath = new File(savePath.toString() + "\\\\" + resultFileName);

            file.transferTo(finalPath);

            if (finalPath.exists()) {
                log.info("File was uploaded as: " + finalPath.toString());

                redirectAttributes.addFlashAttribute("message",
                        "Вы успешно загрузили " + file.getOriginalFilename() + "!");

                cityPhotoService.save(new CityPhoto(resultFileName, city));
            }
        }

        model.addAttribute("city", city);
        return "redirect:/cities/{cityId}/photo/add";
    }

    @GetMapping("/cities/{cityId}/photos/{photoId}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getPhotoDeletePage(
            @PathVariable("cityId") City city,
            @PathVariable("photoId") CityPhoto cityPhoto,
            Model model) {
        List<CityPhoto> cityPhotos = cityPhotoService.getList(city);
        model.addAttribute("cityPhotos", cityPhotos);
        model.addAttribute("cityPhoto", cityPhoto);
        model.addAttribute("city", city);
        model.addAttribute("isDeleting", true);
        return "add_city_photo";
    }

    @DeleteMapping("/cities/{cityId}/photos/{photoId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deletePhoto(@PathVariable("cityId") Long cityId,
                              @PathVariable("photoId") CityPhoto cityPhoto) {
        cityPhotoService.delete(cityPhoto);

        return "redirect:/cities/{cityId}/photo/add";
    }
}
