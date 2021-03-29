package com.chao.Service;

import com.chao.Pojo.Blog;
import com.chao.Pojo.Tag;
import com.chao.Vo.BlogVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

     Blog getBlog(Long id);

     Page<Blog> listBlog(Pageable pageable, BlogVo blog);

     Page<Blog> listBlog(Pageable pageable);

     Page<Blog> listBlog(String query,Pageable pageable);

     Page<Blog> listBlog(Long id,Pageable pageable);

     public Map<String, List<Blog>> archiveBlog();

     Long countBlog();

     /**
      * 查询推荐的博客
      * @param size
      * @return
      */
     List<Blog> listBlog(Integer size);

     /**
      * 得到博客并转换blog.content为markdaown格式
      * @param id
      * @return
      */
     Blog getAndConvert(Long id);

     Blog updateBlog(Blog blog);

     Blog saveBlog(Blog blog);

     void deleteBlog(Long id);
}
