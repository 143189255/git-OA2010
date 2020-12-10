package com.ssm.service;

import com.ssm.pojo.Perssion;

import java.util.List;
import java.util.Set;

public interface PerssionService {
    //获取登录人的菜单
    List<Perssion> getMenus(long empId);
    //获取登录人的操作权限
    List<Perssion> getPermissions(long empId);
}
