package com.ssm.service.Impl;

import com.ssm.mapper.PerssionMapper;
import com.ssm.pojo.Perssion;
import com.ssm.service.PerssionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PerssionServiceImpl implements PerssionService {
    @Autowired
    private PerssionMapper perssionMapper;
    @Override
    public List<Perssion> getMenus(long empId) {

        return perssionMapper.getMenus(empId);
    }

    @Override
    public List<Perssion> getPermissions(long empId) {
        return perssionMapper.getPermissions(empId);
    }
}
