package com.chao.Dao;

import com.chao.Pojo.Blog;
import com.chao.Pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {

    @Query("select t from Blog t where t.recommend=true")
    List<Blog> findTop(Pageable pageable);

}
