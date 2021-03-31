package com.chao.Dao;

import com.chao.Pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CodeChao
 * @date 2021-03-30 21:17
 */

public interface FriendRepository extends JpaRepository<Friend,Long> {
}
