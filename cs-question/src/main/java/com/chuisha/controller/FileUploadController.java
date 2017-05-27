package com.chuisha.controller;

import com.chuisha.conf.CSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;


@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private CSProperties properties;

    @RequestMapping("/upload")
    public ModelAndView addUser(@RequestParam("editormd-image-file") CommonsMultipartFile file, HttpServletRequest request) {
        boolean flag = true;
        String msg = "", fileName = "";
        if (!file.isEmpty()) {
            try {
                fileName = new Date().getTime() + file.getOriginalFilename();
                file.transferTo(new File(properties.getFileSaveDir() + fileName));
            } catch (IOException e) {
                e.printStackTrace();
                flag = false;
                msg = e.getLocalizedMessage();
            }
        } else {
            flag = false;
            msg = "上传文件为空！";
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("success", flag ? 1 : 0);
        mv.addObject("message", msg);
        mv.addObject("url", "fileDownload/" + fileName);
        return mv;
//editor.md期望得到一个json格式的上传后的返回值，格式是这样的：
/*
{
    success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
    message : "提示的信息，上传成功或上传失败及错误信息等。",
    url     : "图片地址"        // 上传成功时才返回
}
*/
    }

}
