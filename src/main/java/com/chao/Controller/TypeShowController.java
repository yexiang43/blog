package com.chao.Controller;

import com.chao.Pojo.Type;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author CodeChao
 * @date 2021-03-29 19:59
 */

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String showTypes(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
                                        @PathVariable Long id, Pageable pageable, Model model)
    {
        List<Type> types = typeService.listType(10000);
        model.addAttribute("types",types);

        if (id==-1)
        {
          id= types.get(0).getId();
        }
        BlogVo blogVo=new BlogVo();
        blogVo.setTypeId(id);
        model.addAttribute("page",blogService.listBlog(pageable,blogVo));
        model.addAttribute("actionTypeId",id);

        return "types";
    }
}
