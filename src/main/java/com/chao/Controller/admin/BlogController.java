package com.chao.Controller.admin;

import com.chao.Pojo.Blog;
import com.chao.Service.BlogService;
import com.chao.Service.TypeService;
import com.chao.Vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CodeChao
 * @date 2021-03-25 21:52
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    /**
     * 博客列表
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String listBlog(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                       Pageable pageable, BlogVo blog, Model model)
    {
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        model.addAttribute("types",typeService.list());
        return "admin/blogs";
    }


    /**
     * 博客列表局部更新
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String listBlogSearch(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, BlogVo blog, Model model)
    {
        model.addAttribute("page",blogService.listBlog(pageable,blog));

        return "admin/blogs :: blogList";
    }
}
