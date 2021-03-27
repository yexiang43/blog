package com.chao.Controller.admin;

import com.chao.Vo.FileVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CodeChao
 * @date 2021-03-26 22:38
 */
@Controller
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileVo upload()
    {
        FileVo fileVo=new FileVo();
        fileVo.setSuccess(1);
        fileVo.setUrl("/images/wechat.jpg");

        return fileVo;
    }
}
