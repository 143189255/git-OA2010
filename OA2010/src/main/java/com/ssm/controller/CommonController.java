package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
//    @RequestMapping("/toIndex")
//    public String toIndex(){
//        return "/WEB-INF/JSP/index.jsp";
//    }
//    @RequestMapping("/toClock")
//    public String toClock(){
//        return "/WEB-INF/JSP/clock.jsp";
//    }
    //通用的页面跳转方法
    @RequestMapping("/page_{page}")
    public String toCommon(@PathVariable("page") String page){
        return page;
    }
}
