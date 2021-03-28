package com.chao.Service;


import com.chao.Pojo.Tag;
import com.chao.Pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 标签
 */
public interface TagService {

    /**
     * 保存
     * @param tag
     * @return
     */
    public Tag saveTag(Tag tag);

    /**
     * 查询一个
     * @param id
     * @return
     */
    public Tag getTag(Long id);

    /**
     * 查询所有分类
     */
    public  Page<Tag> ListTag(Pageable pageable);


    List<Tag> list();

    /**
     * 查询前size个标签
     * @param size
     * @return
     */
    List<Tag> listTag(Integer size);

    /**
     * 修改
     * @param tag
     * @return
     */
    public Tag updateTag(Tag tag);

    /**
     * 删除
     * @param id
     */
    public void deleteTag(Long id);


    List<Tag> getTags(String ids);
}
