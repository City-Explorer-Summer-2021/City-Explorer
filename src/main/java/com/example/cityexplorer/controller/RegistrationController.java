package com.example.cityexplorer.controller;

import com.example.cityexplorer.exception.UsernameAlreadyTakenException;
import com.example.cityexplorer.model.Role;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "registration";
    }

    @PostMapping
    public String addUser(
            @RequestParam(value = "password2") String passwordConfirm,
            @Valid @ModelAttribute("newUser") User user,
            BindingResult bindingResult,
            Model model) {
        boolean confirmEmpty = ObjectUtils.isEmpty(passwordConfirm);

        boolean passwordDiff = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        if (confirmEmpty || passwordDiff || bindingResult.hasErrors()) {
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Set.of(Role.USER));
        try {
            userService.save(user);
        } catch (UsernameAlreadyTakenException e) {
            return "registration";
        } catch (Exception e) {
            return "registration";
        }
        return "registration_success";
    }
}
