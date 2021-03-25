package com.chao.Service;


import com.chao.Pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    /**
     * 保存
     * @param type
     * @return
     */
    public Type saveType(Type type);

    /**
     * 查询一个
     * @param id
     * @return
     */
    public Type getType(Long id);

    /**
     * 查询所有分类
     */
    public  Page<Type> ListType(Pageable pageable);

    /**
     * 修改
     * @param type
     * @return
     */
    public Type updateType(Type type);

    /**
     * 删除
     * @param id
     */
    public void deleteType(Long id);
}
