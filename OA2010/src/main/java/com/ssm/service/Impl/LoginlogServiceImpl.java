package com.ssm.service.Impl;

import com.ssm.mapper.LoginlogMapper;
import com.ssm.pojo.Loginlog;
import com.ssm.service.LoginlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginlogServiceImpl implements LoginlogService {
@Resource
private LoginlogMapper loginlogMapper;

    @Override
    public int addLoginlog(Loginlog loginlog) {
        return loginlogMapper.addLoginlog(loginlog);
    }
//查询最近十条数据
    @Override
    public List<Loginlog> getLastLog(String no) {
        List<Loginlog> loginlogs = loginlogMapper.getLastLog(no);
        return loginlogs;
    }
}
