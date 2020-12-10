package com.ssm.service;

import com.ssm.pojo.Emp;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface EmpService {
    /**
     * 01-登录
     * @return
     */
    public Emp login(Emp emp);
    //添加员工
   public int addEmp(Emp emp,Integer[] rids);
    //    分页
    int getTotalCount();
    //    查询每页数据
    List<Emp> getEmps(@Param("pageStart") long pageStrat, @Param("pageSize") int pageSize);
    //头像更改
    int updatePhoto(Emp emp);
    //删除用户
    int deleteEmp(int id);
    //查询员工总人数
    int totalEmp();
    //编辑员工信息
    int empupdate(Emp emp,Integer[] roleIds);
    //获取所有员工工号
    List<String> getAllEmpNo();
}
