package com.chao.Controller;

import com.chao.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

/**
 * @author CodeChao
 * @date 2021-03-30 20:16
 */

@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;

    @RequestMapping("/friend")
    public String friend(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                     Pageable pageable, Model model)
    {
        model.addAttribute("friends",friendService.ListFriend(pageable));
        return "friend";
    }
}
