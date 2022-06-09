package com.example.weatherapplication.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private JavaMailSender javaMailSender;


    @GetMapping
    private String getPage(@RequestParam(required = false) String success, Model model){
        if (success != null && !success.isEmpty()) {
            model.addAttribute("isSuccess", true);
            model.addAttribute("success", success);
        }
        model.addAttribute("bodyContent", "contact");
        return "master-template";
    }


    @PostMapping("/send")
    public String sendEmail(@RequestParam String email,
                            @RequestParam String message){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("dasproekt7@gmail.com");

        msg.setSubject(String.format("Email from: %s", email));
        msg.setText(message);

        javaMailSender.send(msg);
        return "redirect:/contact?success=" + "Thanks for filling out our form!";
    }
}
