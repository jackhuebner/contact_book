package com.creightoncoders.contactbook.WebController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "web_page"; // maps to src/main/resources/templates/greetings.html
    }
}