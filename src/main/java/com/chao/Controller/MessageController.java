package com.chao.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CodeChao
 * @date 2021-03-31 19:27
 */
@Controller
public class MessageController {

    @RequestMapping("/messages")
    public String message()
    {
        return "messages";
    }
}
