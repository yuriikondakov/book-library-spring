package ua.edu.springLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {

    @GetMapping("/international")
    public String getInternationalPage() {
        return "index";
    }

}
