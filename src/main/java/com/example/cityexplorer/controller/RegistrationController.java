package com.example.cityexplorer.controller;

import com.example.cityexplorer.controller.ControllerUtils;
import com.example.cityexplorer.exception.UsernameAlreadyTakenException;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {
        boolean confirmEmpty = ObjectUtils.isEmpty(passwordConfirm);
//        if (confirmEmpty) {
//            model.addAttribute("password2Error", "Passwords confirmation cannot be empty");
//        }
        boolean passwordDiff = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);
//        if (passwordDiff) {
//            model.addAttribute("passwordError", "Passwords are different");
//        }

        if (confirmEmpty || passwordDiff || bindingResult.hasErrors()) {
//            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errors);
            return "registration";
        }

        user.setActive(true);
        try {
            userService.save(user);
        } catch (UsernameAlreadyTakenException e) {
//            model.addAttribute("nameError", "User exists!");
            return "registration";
        } catch (Exception e) {
            return "registration";
        }

        return "redirect:/login";
    }
}
