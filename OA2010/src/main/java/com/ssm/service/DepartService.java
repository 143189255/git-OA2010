package com.ssm.service;

import com.ssm.pojo.Depart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartService {
    int addDepart(Depart depart);
    //    分页
    int getTotalCount();
    //    查询每页数据
    List<Depart> getDeparts(@Param("pageStart") long pageStrat, @Param("pageSize") int pageSize);

    int deleteDepart(int id);
    //更改部门信息
    int departUpdateById(Depart depart);
    //获取所有部门
    List<Depart> findAllDeparts();
    //根据部门ID获取部门信息
    Depart getDepartById(long id);
    //根据部门id获取部门总人数
    int getDepartCountById(long id);
}
