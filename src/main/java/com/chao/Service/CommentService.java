package com.chao.Service;


import com.chao.Pojo.Comment;
import com.chao.Pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    List<Comment> findCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteComment(Long id);

    /**
     * 评论列表
     */
    Page<Comment> ListComment(Pageable pageable);
}
