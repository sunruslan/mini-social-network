package com.cis.minisocialnetwork.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public String home(){
        return "redirect:/users/sigin";
    }
}
