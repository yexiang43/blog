package com.chao.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CodeChao
 * @date 2021-03-25 22:59
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogVo {

    private Long typeId;
    private String title;
    private boolean recommend;
}
