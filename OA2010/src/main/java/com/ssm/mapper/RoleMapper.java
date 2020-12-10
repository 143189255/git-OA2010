package com.ssm.mapper;

import com.ssm.pojo.Role;

import java.util.List;

public interface RoleMapper {
    //根据员工ID查询角色
    List<Role> getRolesByEmpId(long empId);

}
