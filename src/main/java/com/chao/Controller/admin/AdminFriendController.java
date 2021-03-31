package com.chao.Controller.admin;


import com.chao.Pojo.Friend;
import com.chao.Service.FriendService;
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
 * @author CodeChao
 * @date 2021-03-30 22:02
 */

@Controller
@RequestMapping("/admin")
public class AdminFriendController {

    @Autowired
    private FriendService friendService;

    /**
     * 标签列表
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/friends")
    public String list(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model)
    {
        model.addAttribute("page",friendService.ListFriend(pageable));
        return "admin/friends";
    }

    /**
     * 新增跳转
     * @return
     */
    @GetMapping("/input/friend")
    public String input()
    {
        return "admin/friends-input";
    }

    @GetMapping("/friends/input")
    public String input(Model model) {
        model.addAttribute("friend", new Friend());
        return "admin/friends-input";
    }
    /**
     * 修改页面跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/friends/{id}/input")
    public String updateFriend(@PathVariable Long id, Model model)
    {
        Friend t = friendService.getFriend(id);
        model.addAttribute("friend",t);
        model.addAttribute("friendName",t.getFriendName());
        model.addAttribute("url",t.getUrl());
        model.addAttribute("avatar",t.getAvatar());
        return "admin/friends-input";
    }

    /**
     * 删除
     * @param id
     * @param attribute
     * @return
     */
    @RequestMapping("/friends/{id}/delete")
    public String deleteFriend(@PathVariable Long id, RedirectAttributes attribute)
    {
        friendService.deleteFriend(id);
        attribute.addFlashAttribute("message","删除成功");
        return "redirect:/admin/friends";
    }

    /**
     * 添加标签
     * @param friend
     * @return
     */
    @PostMapping("/friends")
    public String saveFriend(Friend friend,RedirectAttributes attribute)
    {
        System.out.println("---post---");
        Friend t = friendService.saveFriend(friend);

        if (t==null)
        {
            attribute.addFlashAttribute("message","添加失败");
        }
        else
        {
            attribute.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/friends";
    }

    /**
     * 保存修改的标签
     * @param friend
     * @return
     */
    @PostMapping("/friends/{id}")
    public String saveUpdateFriend(Friend friend,RedirectAttributes attribute)
    {
        //  System.out.println("---post---");
        Friend t = friendService.saveFriend(friend);

        if (t==null)
        {
            attribute.addFlashAttribute("message","修改失败");
        }
        else
        {
            attribute.addFlashAttribute("message","修改成功");
        }

        return "redirect:/admin/friends";
    }
    
}
