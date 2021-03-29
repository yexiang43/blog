package com.chao.Controller;

import com.chao.Pojo.Comment;
import com.chao.Pojo.User;
import com.chao.Service.BlogService;
import com.chao.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author CodeChao
 * @date 2021-03-28 19:56
 */
@Controller
public class CommentController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comment(@PathVariable Long blogId, Model model)
    {
        model.addAttribute("comments",commentService.findCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public  String post(Comment comment, HttpSession session)
    {
        Long blogId = comment.getBlog().getId();

        comment.setBlog(blogService.getBlog(blogId));

        User user = (User) session.getAttribute("user");

        if(user!=null)
        {
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setAdminComment(true);
        }else
        {
            comment.setAvatar(avatar);
        }

        commentService.saveComment(comment);

        return "redirect:/comments/"+blogId;
    }
}
