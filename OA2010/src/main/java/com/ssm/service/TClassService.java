package com.ssm.service;

import com.ssm.pojo.TClass;

import java.util.List;

public interface TClassService {
    List<TClass> getClasses();
    //新增班级
    int addClass(TClass t);
    //获取所有班级信息
    List<TClass> getAllClass();
    //获取班级个数
    int getTotalCount();
}
