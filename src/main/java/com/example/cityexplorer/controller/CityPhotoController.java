package com.example.cityexplorer.controller;

import com.example.cityexplorer.model.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class CityPhotoController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/cities/{cityId}/photo/add")
    public String addPhotoPage(@PathVariable("cityId") City city,
            Model model) {

        model.addAttribute("city", city);

        return "add_city_photo";
    }

    @PostMapping("/cities/{cityId}/photo/add")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @PathVariable("cityId") City city,
            RedirectAttributes redirectAttributes,
            Model model) throws IOException {

        System.out.println("POST METH FILE ");

        if (file != null) {
            File savePath = new File(uploadPath);

            System.out.println("PATH IS " + uploadPath);

            if (!savePath.exists()) {
                boolean mkdir = savePath.mkdirs();
                System.out.println("PATH AFTER MKDIR " + savePath + "is created? : " + mkdir );
            }

            if(savePath.exists()){
                System.out.println("EXISTS");
            }


            String uuid = UUID.randomUUID().toString();
            String resultFileName = uuid + "." + file.getOriginalFilename();

            System.out.println("NAME MUST BE " + resultFileName);
            System.out.println("BYTES " +file.getBytes().length);

//            Path path = Paths.get(uploadPath+resultFileName);
            File pa = new File(uploadPath  + resultFileName);

            file.transferTo(pa);

        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        model.addAttribute("city", city);

        return "redirect:/cities/{cityId}/photo/add";
    }

}