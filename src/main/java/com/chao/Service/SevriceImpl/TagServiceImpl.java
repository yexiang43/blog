package com.chao.Service.SevriceImpl;

import com.chao.Dao.TagRepository;
import com.chao.Exception.NotFoundException;
import com.chao.Pojo.Tag;
import com.chao.Service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Tag> ListTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> list() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> getTags(String ids) {
        List<Tag> TagList=new ArrayList<>();

        //ibs 装换成数组，在进行一一查询 加入List
        String[] id = ids.split(",");
        for (String s : id) {
            Tag tag = tagRepository.getOne(Long.valueOf(s));
            TagList.add(tag);
        }

        return TagList;
    }

    @Transactional
    @Override
    public Tag updateTag(Tag tag) {
        Tag t = tagRepository.getOne(tag.getId());

        if(t==null)
        {
            throw new NotFoundException("不存在该类型！");
        }

        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
         tagRepository.deleteById(id);
    }


    @Override
    public List<Tag> listTag(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");

        Pageable pageable= PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }
}
