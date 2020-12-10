package com.ssm.controller;

import com.ssm.utils.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

@Controller
public class uploadController {
    @RequestMapping("/photoupload")
    @ResponseBody
    public ResultMessage photoupload(MultipartFile file,HttpSession session)throws Exception{
        String oldName = file.getOriginalFilename();
        System.out.println("要导入的文件原始名称："+oldName);
        // 获得文件后缀名称
        String suffixName = oldName.substring(oldName.lastIndexOf(".") + 1).toLowerCase();
// 生成最新的uuid文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newName = uuid + "."+ suffixName;
        //获取文件上传的真实的文件服务器路径
        String realPath = session.getServletContext().getRealPath("/media/upload");
           realPath=realPath+"\\"+newName;
        System.out.println("真实的要上传的文件名称："+realPath);
        //实现文件上传
        file.transferTo(new File(realPath));
        System.out.println("文件上传成功");
        System.out.println(newName);
        ResultMessage resultMessage = new ResultMessage(100,newName);

        return resultMessage;
    }
}
