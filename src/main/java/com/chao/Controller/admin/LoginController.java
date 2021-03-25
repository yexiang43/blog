package com.chao.Controller.admin;

import com.chao.Pojo.User;
import com.chao.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录
     */
    @GetMapping
    public String loginPage()
    {
        return "admin/login";
    }

    /**
     * 后台登录
     * @param username
     * @param password
     * @param attribute
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password
                        , RedirectAttributes attribute
                        , HttpSession session)
    {
        User user = userService.queryUser(username, password);

        if (user!=null)
        {
            //把用户添加session中 并把密码清空
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else
        {
            attribute.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }

    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logOut(HttpSession session)
    {
        session.removeAttribute("user");
        System.out.println("---注销---");
        return "redirect:/admin";
    }

}
