package com.chao.Service.SevriceImpl;

import com.chao.Dao.IndexRepository;
import com.chao.Exception.NotFoundException;
import com.chao.Pojo.Index;
import com.chao.Service.IndexService;
import com.chao.Utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CodeChao
 * @date 2021-03-30 17:48
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexRepository indexRepository;

    @Override
    public Index queryIndex(Long id) {
        return indexRepository.getOne(id);
    }

    @Override
    public Index updateIndex(Index index) {
        Index i = indexRepository.getOne(index.getId());
        if(i==null)
        {
            throw new NotFoundException("不存在该类型！");
        }

        BeanUtils.copyProperties(index,i, MyBeanUtils.getNullPropertyNames(index));
        i.setIndexViews(i.getIndexViews()+1);
        return indexRepository.save(i);
    }
}
