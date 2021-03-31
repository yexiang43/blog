package com.chao.Service;


import com.chao.Pojo.Friend;
import com.chao.Pojo.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FriendService {

    /**
     * 保存
     * @param friend
     * @return
     */
    public Friend saveFriend(Friend friend);

    /**
     * 查询一个
     * @param id
     * @return
     */
    public Friend getFriend(Long id);

    /**
     * 查询所有
     */
    public  Page<Friend> ListFriend(Pageable pageable);


    List<Friend> list();

    /**
     * 修改
     * @param friend
     * @return
     */
    public Friend updateFriend(Friend friend);

    /**
     * 删除
     * @param id
     */
    public void deleteFriend(Long id);



    
}
