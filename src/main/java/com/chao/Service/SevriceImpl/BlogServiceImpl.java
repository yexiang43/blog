package com.chao.Service.SevriceImpl;

import com.chao.Dao.BlogRepository;
import com.chao.Exception.NotFoundException;
import com.chao.Pojo.Blog;
import com.chao.Pojo.Tag;
import com.chao.Service.BlogService;
import com.chao.Utils.MarkdownUtils;
import com.chao.Utils.MyBeanUtils;
import com.chao.Vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.beans.Transient;
import java.sql.Blob;
import java.util.*;

/**
 * @author CodeChao
 * @date 2021-03-25 21:15
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogVo blog) {


        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();

                if (!" ".equals(blog.getTitle()) && blog.getTitle()!=null)
                {
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }

                if (blog.getTypeId()!=null)
                {
                    predicates.add(cb.equal(root.<String>get("type").get("id"),blog.getTypeId()));
                }

                if(blog.isRecommend())
                {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }


    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        Blog t = blogRepository.getOne(blog.getId());
        if(t==null)
        {
            throw new NotFoundException("不存在该类型！");
        }

        BeanUtils.copyProperties(blog,t, MyBeanUtils.getNullPropertyNames(blog));
        t.setUpdateTime(new Date());
        return blogRepository.save(t);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    @Override
    public Blog saveBlog(Blog blog) {
          if (blog.getId()==null)
          {
              blog.setCreateTime(new Date());
              blog.setUpdateTime(new Date());
              blog.setViews(0);
          }else
          {
              blog.setUpdateTime(new Date());
          }
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
         blogRepository.deleteById(id);
    }


    @Override
    public List<Blog> listBlog(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"updateTime");

        Pageable pageable= PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    @Transient
    @Override
    public Blog getAndConvert(Long id) {
        Blog b=new Blog();

        Blog blog = blogRepository.getOne(id);
        if (blog==null)
        {
            throw new NotFoundException("博客不存在！");
        }

        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogRepository.updateViews(id);
        return b;
    }
}
