package com.chao.Controller.admin;

import com.chao.Vo.FileVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author CodeChao
 * @date 2021-03-26 22:38
 */
@Controller
public class FileController {


   // @RequestMapping("/file/upload")
    @ResponseBody
    public FileVo  fileUpload2(@RequestParam(name = "editormd-image-file", required = false) CommonsMultipartFile file, HttpServletRequest request)
            throws IOException, IOException {

        //上传路径保存设置
        String path = request.getServletContext().getRealPath("/static/upload");
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }
        //上传文件地址
        System.out.println("上传文件保存地址："+realPath);

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(new File(realPath +"/"+ file.getOriginalFilename()));

        FileVo fileVo=new FileVo();
        fileVo.setSuccess(1);
        fileVo.setUrl(realPath.toString());

        return fileVo;
    }

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileVo upLoad(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file
    ) {
        FileVo object = new FileVo();
        try {
            //先创建文件夹
            //先获取在当前系统下的文件夹路径
            String osName = System.getProperty("os.name");
            String realPath;
            realPath = "/Volumes/mac-Code/项目/个人博客/blog/src/main/resources/static/upload/";
            File myFlie = new File(realPath);
            if (!myFlie.exists()) {
                myFlie.mkdirs();
            }
            //获取上传文件的文件名
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid + "_" + fileName;
            file.transferTo(new File(realPath, fileName));
            object.setSuccess( 1);
            object.setMessage( "上传成功");
            object.setUrl( "/upload/" + fileName);

        } catch (Exception e) {
            e.printStackTrace();
            object.setSuccess( 0);
            object.setMessage("上传失败");
        }

        return object;

    }

}
