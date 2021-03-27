package com.chao.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CodeChao
 * @date 2021-03-26 22:10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVo {

    private int success;
    private String message;
    private String url;

}
