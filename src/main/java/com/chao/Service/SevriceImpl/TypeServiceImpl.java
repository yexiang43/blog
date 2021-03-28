package com.chao.Service.SevriceImpl;

import com.chao.Dao.TypeRepository;
import com.chao.Exception.NotFoundException;
import com.chao.Pojo.Type;
import com.chao.Service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> ListType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Type type) {
        Type t = typeRepository.getOne(type.getId());

        if(t==null)
        {
            throw new NotFoundException("不存在该类型！");
        }

        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
         typeRepository.deleteById(id);
    }


    @Override
    public List<Type> list() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listType(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");

        Pageable pageable=PageRequest.of(0, size, sort);
        return typeRepository.findTop(pageable);
    }
}
