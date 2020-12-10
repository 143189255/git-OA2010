package com.ssm.mapper;

import com.ssm.pojo.Depart;
import com.ssm.pojo.Emp;
import com.ssm.utils.TongJi;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EmpMapper {
    public Emp login(Emp emp);

   public int addEmp(Emp emp);
    //    分页
    int getTotalCount();
    //    查询每页数据
    List<Emp> getEmps(@Param("pageStart") long pageStrat, @Param("pageSize") int pageSize);
    //获取统计数据
    List<TongJi> getData();
    //头像更改
    int updatePhoto(Emp emp);
    //删除用户
    int deleteEmp(int id);
    //给用户添加角色
   public int addEmpRole(@Param("empId") long empId,@Param("rids") long rids);
   //查询员工总人数
    int totalEmp();
    //编辑员工信息
    int empupdate(Emp emp);
    //根据员工ID删除对应的角色
    int deleteRoles(long empid);
    //获取所有员工工号
    List<String> getAllEmpNo();
}
