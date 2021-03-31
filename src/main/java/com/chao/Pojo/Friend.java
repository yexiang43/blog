package com.chao.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeChao
 * @date 2021-03-30 21:08
 */
@Entity
@Table(name = "t_friend")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue
    private Long id;

    private String friendName;
    private String url;
    private String avatar;
}
