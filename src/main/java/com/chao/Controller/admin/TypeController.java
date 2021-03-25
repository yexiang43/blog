package com.chao.Controller.admin;

import com.chao.Pojo.Type;
import com.chao.Service.TypeService;
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
 * 分类控制器
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分类列表
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String list(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model)
    {
//        System.out.println("---get---");
        model.addAttribute("page",typeService.ListType(pageable));
        return "admin/types";
    }

    /**
     * 新增跳转
     * @return
     */
    @GetMapping("/input")
    public String input()
    {
        return "admin/types-input";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }
    /**
     * 修改页面跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String updateType(@PathVariable Long id, Model model)
    {
        Type t = typeService.getType(id);
        model.addAttribute("type",t);
        model.addAttribute("name",t.getName());
        return "admin/types-input";
    }

    /**
     * 删除
     * @param id
     * @param attribute
     * @return
     */
    @RequestMapping("/types/{id}/delete")
   public String deleteType(@PathVariable Long id,RedirectAttributes attribute)
   {
       typeService.deleteType(id);
       attribute.addFlashAttribute("message","删除成功");
       return "redirect:/admin/types";
   }

    /**
     * 添加分类
     * @param type
     * @return
     */
    @PostMapping("/types")
    public String saveType(Type type,RedirectAttributes attribute)
    {
       //  System.out.println("---post---");
        Type t = typeService.saveType(type);

        if (t==null)
        {
            attribute.addFlashAttribute("message","添加失败");
        }
        else
        {
            attribute.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/types";
    }

    /**
     * 保存修改的分类
     * @param type
     * @return
     */
    @PostMapping("/types/{id}")
    public String saveUpdateType(Type type,RedirectAttributes attribute)
    {
        //  System.out.println("---post---");
        Type t = typeService.saveType(type);

        if (t==null)
        {
            attribute.addFlashAttribute("message","修改失败");
        }
        else
        {
            attribute.addFlashAttribute("message","修改成功");
        }

        return "redirect:/admin/types";
    }
}
