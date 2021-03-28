package com.chao.Controller;

import com.chao.Pojo.User;
import com.chao.Service.BlogService;
import com.chao.Service.TagService;
import com.chao.Service.TypeService;
import com.chao.Service.UserService;
import com.chao.Vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model)
    {
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("tags",tagService.listTag(8));
        model.addAttribute("types",typeService.listType(6));
        model.addAttribute("recommendBlogs",blogService.listBlog(6));
        List<User> user = userService.getUser();
        model.addAttribute("user",user.get(0));
        //System.out.println(blogService.listBlog(6));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog() {
        return "blog";
    }


    @RequestMapping("/about")
    public  String about()
    {
        return "/about";
    }
}
