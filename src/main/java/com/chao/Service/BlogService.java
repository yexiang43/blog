package com.chao.Service;

import com.chao.Pojo.Blog;
import com.chao.Vo.BlogVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {

     Blog getBlog(Long id);

     Page<Blog> listBlog(Pageable pageable, BlogVo blog);

     Blog updateBlog(Blog blog);

     Blog saveBlog(Blog blog);

     void deleteBlog(Long id);
}
