package com.chao.Controller.admin;

import com.chao.Pojo.Blog;
import com.chao.Pojo.Tag;
import com.chao.Pojo.User;
import com.chao.Service.BlogService;
import com.chao.Service.TagService;
import com.chao.Service.TypeService;
import com.chao.Vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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

    @Autowired
    private TagService tagService;

    /**
     * 博客列表
     *
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String listBlog(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, BlogVo blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("types", typeService.list());
        return "admin/blogs";
    }


    /**
     * 博客列表局部更新
     *
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String listBlogSearch(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                         Pageable pageable, BlogVo blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));

        return "admin/blogs :: blogList";
    }

    /**
     * 新增博客跳转器
     *
     * @return
     */
    @RequestMapping("/blog/input")
    public String blogInput(Model model) {
        model.addAttribute("types", typeService.list());
        model.addAttribute("tags", tagService.list());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    /**
     * 新增博客
     *
     * @param
     * @return
     */
    @PostMapping("/blogs")
    public String saveBlog(Blog blog, RedirectAttributes attribute, HttpSession session) {
        //添加用户信息
        blog.setUser((User) session.getAttribute("user"));
        //潜入分类
        blog.setType(typeService.getType(blog.getType().getId()));
        //添加标签
        blog.setTags(tagService.getTags(blog.getTagIds()));
        Blog b;

        if (blog.getId()==null)
        {
            b=blogService.saveBlog(blog);
        }else {
            b=blogService.updateBlog(blog);
        }

        if (b == null) {
            attribute.addFlashAttribute("message", "操作失败");
        } else {
            attribute.addFlashAttribute("message", "操作成功");
        }

        return "redirect:/admin/blogs";
    }

    /**
     * 删除
     *
     * @param id
     * @param attribute
     * @return
     */
    @RequestMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attribute) {
        blogService.deleteBlog(id);
        attribute.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }


    /**
     * 修改博客跳转
     *
     * @param
     * @return
     */
    @RequestMapping("/blogs/{id}/input")
    public String updateBlog(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.list());
        model.addAttribute("tags", tagService.list());
        Blog blog = blogService.getBlog(id);
        blog.init();//初始化IdS
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }
}
