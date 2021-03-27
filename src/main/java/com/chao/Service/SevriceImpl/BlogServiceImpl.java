package com.chao.Service.SevriceImpl;

import com.chao.Dao.BlogRepository;
import com.chao.Exception.NotFoundException;
import com.chao.Pojo.Blog;
import com.chao.Pojo.Tag;
import com.chao.Service.BlogService;
import com.chao.Vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Blog updateBlog(Blog blog) {
        Blog t = blogRepository.getOne(blog.getId());

        if(t==null)
        {
            throw new NotFoundException("不存在该类型！");
        }

        BeanUtils.copyProperties(blog,t);
        return blogRepository.save(t);
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
}
