package com.ssm.mapper;

import com.ssm.pojo.Loginlog;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface LoginlogMapper {
// 添加日志
   public int addLoginlog(Loginlog loginlog);
   //查询最近十条信息
    List<Loginlog> getLastLog(String no);
}
