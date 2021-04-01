package com.chao.Dao;

import com.chao.Pojo.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findCommentByBlogId(Long blogId, Sort sort);

    //List<Comment> findByBlogIdAndParentCommentNull(Pageable pageable, Long blogId, Sort sort);

    List<Comment> findByBlogIdAndParentCommentNull(Pageable pageable,Long blogId);
}
