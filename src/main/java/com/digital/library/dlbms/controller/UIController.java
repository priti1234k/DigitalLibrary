package com.digital.library.dlbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/")
    public String viewBookController() {
        return "redirect:/index.html";
    }
}
