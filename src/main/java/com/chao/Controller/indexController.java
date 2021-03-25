package com.chao.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/about")
    public String about()
    {
        return "about";
    }
}
