package com.chao.Controller;

import com.chao.Pojo.Tag;
import com.chao.Service.BlogService;
import com.chao.Service.TagService;
import com.chao.Vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author CodeChao
 * @date 2021-03-29 20:36
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String showTags(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
                           @PathVariable Long id, Pageable pageable, Model model)
    {

        List<Tag> tags = tagService.listTag(10000);
        model.addAttribute("tags",tags);

        if (id==-1)
        {
            id= tags.get(0).getId();
        }

        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("actionTagId",id);
        return "tags";
    }
}
