package com.chao.Service;


import com.chao.Pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
