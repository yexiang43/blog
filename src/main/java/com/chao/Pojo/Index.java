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
 * @date 2021-03-30 17:43
 */
@Entity
@Table(name = "t_index")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Index {

    @Id
    @GeneratedValue
    private Long id;

    private Long indexViews;

}
