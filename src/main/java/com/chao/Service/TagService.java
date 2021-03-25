package com.chao.Service;


import com.chao.Pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}
