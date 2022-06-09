package com.example.weatherapplication.web.controller;


import com.example.weatherapplication.model.exceptions.InvalidArgumentException;
import com.example.weatherapplication.model.exceptions.PasswordDoNotMatchException;
import com.example.weatherapplication.model.exceptions.UsernameAlreadyExistsException;
import com.example.weatherapplication.service.AuthService;
import com.example.weatherapplication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname){
        try{
            this.userService.register(username, password, repeatPassword, name, surname);
            return "redirect:/login";
        }
        catch(PasswordDoNotMatchException | UsernameAlreadyExistsException | InvalidArgumentException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
