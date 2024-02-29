package ru.lakeevda.shopclient.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.lakeevda.shopclient.service.ShopService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/")
    @CircuitBreaker(name = "homePage", fallbackMethod = "callBackHomePage")
    public String homePage(Model model,
                           @RequestParam(value = "confirm", required = false) String confirm){
        model.addAttribute("products", shopService.getProducts());
        if (confirm != null) model.addAttribute("confirm", confirm);
        return "home";
    }

    private String callBackHomePage(Model model, String confirm, Exception e) {
        model.addAttribute("message", e.getMessage());
        return "home";
    }

    @PostMapping("/buy/{id}")
    @CircuitBreaker(name = "buyProduct", fallbackMethod = "callBackBuyProduct")
    public String buyProduct(@PathVariable("id") Long id,
                             @RequestParam("amount") Integer amount,
                             RedirectAttributes redirectAttributes){
        shopService.buyProduct(id, amount);
        redirectAttributes.addAttribute("confirm", "Покупка успешно совершена!");
        return "redirect:/";
    }

    private String callBackBuyProduct(Long id, Integer amount,
                                      RedirectAttributes redirectAttributes, Exception e) {

        redirectAttributes.addFlashAttribute("message", e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(RuntimeException.class)
    public String errorPage(RuntimeException e, Model model){
        model.addAttribute("message", e.getLocalizedMessage());
        model.addAttribute("products", shopService.getProducts());
        return "home";
    }
}
