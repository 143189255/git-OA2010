package com.ssm.service.Impl;

import com.ssm.mapper.RoleMapper;
import com.ssm.pojo.Role;
import com.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRolesByEmpId(long empId) {
        return roleMapper.getRolesByEmpId(empId);
    }
}
