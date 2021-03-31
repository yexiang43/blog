package com.chao.Service.SevriceImpl;

import com.chao.Dao.FriendRepository;
import com.chao.Dao.FriendRepository;
import com.chao.Exception.NotFoundException;
import com.chao.Pojo.Blog;
import com.chao.Pojo.Friend;
import com.chao.Pojo.Friend;
import com.chao.Service.FriendService;
import com.chao.Utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CodeChao
 * @date 2021-03-30 21:21
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository triendRepository;

    @Transactional
    @Override
    public Friend saveFriend(Friend friend) {
        return triendRepository.save(friend);
    }

    @Transactional
    @Override
    public Friend getFriend(Long id) {
        return triendRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Friend> ListFriend(Pageable pageable) {
        return triendRepository.findAll(pageable);
    }

    @Override
    public List<Friend> list() {
        return triendRepository.findAll();
    }



    @Transactional
    @Override
    public Friend updateFriend(Friend triend) {
        Friend t = triendRepository.getOne(triend.getId());

        if(t==null)
        {
            throw new NotFoundException("不存在该类型！");
        }

        BeanUtils.copyProperties(triend,t);
        return triendRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteFriend(Long id) {
        triendRepository.deleteById(id);
    }


}
