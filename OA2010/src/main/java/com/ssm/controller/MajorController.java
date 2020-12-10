package com.ssm.controller;

import com.ssm.pojo.Major;
import com.ssm.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Scope("prototype")
public class MajorController {
    @Autowired
    private MajorService majorService;
    @RequestMapping("/major_getNames")
    @ResponseBody
    public List<Major> getMajorNames(){
        return majorService.getMajors();
    }
}
