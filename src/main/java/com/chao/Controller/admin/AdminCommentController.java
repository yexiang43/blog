package com.chao.Controller.admin;

import com.chao.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author CodeChao
 * @date 2021-03-30 16:01
 */

@Controller
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论列表
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/admin/comments")
    public String CommentList(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model)
    {
        model.addAttribute("page",commentService.ListComment(pageable));
        return "admin/comment";
    }

    /**
     * 删除
     * @param id
     * @param attribute
     * @return
     */
    @RequestMapping("/admin/comments/{id}/delete")
    public String deleteTag(@PathVariable Long id, RedirectAttributes attribute)
    {
        commentService.deleteComment(id);
        attribute.addFlashAttribute("message","删除成功");
        return "redirect:/admin/comments";
    }
}
