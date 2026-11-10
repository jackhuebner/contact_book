package com.creightoncoders.contactbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    Contact contact = new Contact("Jack");

    @GetMapping("/")
    public String index(Model model) {
                model.addAttribute("name", contact);
        return "home_page"; // maps to src/main/resources/templates/home_page.html
    }
}