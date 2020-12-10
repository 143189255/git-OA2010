package com.ssm.service;

import com.ssm.pojo.Loginlog;

import java.util.List;

public interface LoginlogService {
    int addLoginlog(Loginlog loginlog);
    //查询最近十条数据
    List<Loginlog> getLastLog(String no);
}
