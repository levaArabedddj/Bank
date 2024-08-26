package com.example.bank_project.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainPageBank")
public class MainPageUsersControllers {


    @GetMapping
    public String viewPageMain(){
        return "mainPageBank";
    }
}
