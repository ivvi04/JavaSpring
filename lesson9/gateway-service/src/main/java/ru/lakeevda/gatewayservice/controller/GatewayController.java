package ru.lakeevda.gatewayservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GatewayController {

    @RequestMapping("/")
    public String swagger () {
        return "redirect:/swagger-ui.html";
    }
}
