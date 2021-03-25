package com.chao.Controller.admin;

import com.chao.Pojo.Tag;
import com.chao.Service.TagService;
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

/**
 * 标签控制器
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 标签列表
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String list(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model)
    {
    //   System.out.println("---get---");
        model.addAttribute("page",tagService.ListTag(pageable));
        return "admin/tags";
    }

    /**
     * 新增跳转
     * @return
     */
    @GetMapping("/input/tag")
    public String input()
    {
        return "admin/tags-input";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }
    /**
     * 修改页面跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String updateTag(@PathVariable Long id, Model model)
    {
        Tag t = tagService.getTag(id);
        model.addAttribute("tag",t);
        model.addAttribute("name",t.getName());
        return "admin/tags-input";
    }

    /**
     * 删除
     * @param id
     * @param attribute
     * @return
     */
    @RequestMapping("/tags/{id}/delete")
   public String deleteTag(@PathVariable Long id,RedirectAttributes attribute)
   {
       tagService.deleteTag(id);
       attribute.addFlashAttribute("message","删除成功");
       return "redirect:/admin/tags";
   }

    /**
     * 添加标签
     * @param tag
     * @return
     */
    @PostMapping("/tags")
    public String saveTag(Tag tag,RedirectAttributes attribute)
    {
       System.out.println("---post---");
        Tag t = tagService.saveTag(tag);

        if (t==null)
        {
            attribute.addFlashAttribute("message","添加失败");
        }
        else
        {
            attribute.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/tags";
    }

    /**
     * 保存修改的标签
     * @param tag
     * @return
     */
    @PostMapping("/tags/{id}")
    public String saveUpdateTag(Tag tag,RedirectAttributes attribute)
    {
        //  System.out.println("---post---");
        Tag t = tagService.saveTag(tag);

        if (t==null)
        {
            attribute.addFlashAttribute("message","修改失败");
        }
        else
        {
            attribute.addFlashAttribute("message","修改成功");
        }

        return "redirect:/admin/tags";
    }
}
