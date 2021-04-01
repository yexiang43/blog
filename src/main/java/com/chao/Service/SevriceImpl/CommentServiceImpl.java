package com.chao.Service.SevriceImpl;

import com.chao.Dao.CommentRepository;
import com.chao.Pojo.Comment;
import com.chao.Service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CodeChao
 * @date 2021-03-28 20:10
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentByBlogId(Pageable pageable,Long blogId) {
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(pageable,blogId);

        return eachComment(comments);
    }

    @Transient
    @Override
    public Comment saveComment(Comment comment) {

        Long parentId = comment.getParentComment().getId();

        if (parentId!=-1)
        {
            comment.setParentComment(commentRepository.getOne(parentId));
        }
        else {
            comment.setParentComment(null);
        }
       comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }


    @Override
    public void deleteComment(Long id) {
         commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> ListComment(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }


    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
