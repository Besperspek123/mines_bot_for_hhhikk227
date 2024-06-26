package com.example.tgbot.controller;

import com.example.tgbot.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class AviatorAppController {

    @Autowired
    UrlService urlService;

    @GetMapping("/aviator")
    public String aviatorPage(@RequestParam("oneWinUrl") String oneWinUrl, Model model) {
        model.addAttribute("gameUrl", oneWinUrl);
        return "aviator";
    }


}
