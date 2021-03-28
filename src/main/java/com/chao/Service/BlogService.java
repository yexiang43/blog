package com.chao.Service;

import com.chao.Pojo.Blog;
import com.chao.Pojo.Tag;
import com.chao.Vo.BlogVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

     Blog getBlog(Long id);

     Page<Blog> listBlog(Pageable pageable, BlogVo blog);

     Page<Blog> listBlog(Pageable pageable);

     /**
      * 查询推荐的博客
      * @param size
      * @return
      */
     List<Blog> listBlog(Integer size);

     Blog updateBlog(Blog blog);

     Blog saveBlog(Blog blog);

     void deleteBlog(Long id);
}
