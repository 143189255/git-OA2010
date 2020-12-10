package com.ssm.service.Impl;

import com.ssm.mapper.EmpMapper;
import com.ssm.pojo.Emp;
import com.ssm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;

    @Override
    public Emp login(Emp emp) {

        return empMapper.login(emp);
    }
    @Transactional//(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = -1,readOnly = false,noRollbackFor = {ArithmeticException.class})
    @Override
    public int addEmp(Emp emp,Integer[] roleIds) {
        int count = empMapper.addEmp(emp);
        if(count>0){
            //员工新增成功之后，然后新增另外一个角色和员工中间表
            for (Integer roleId : roleIds) {
                empMapper.addEmpRole(emp.getId(),roleId);
            }
        }
        return count;
    }

    @Override
    public int getTotalCount() {
        return empMapper.getTotalCount();
    }
@Transactional
    @Override
    public List<Emp> getEmps(long pageStrat, int pageSize) {
        return empMapper.getEmps(pageStrat,pageSize);
    }
    @Override
    public int updatePhoto(Emp emp) {

        return  empMapper.updatePhoto(emp);

    }

    @Override
    public int deleteEmp(int id) {
        return empMapper.deleteEmp(id);
    }

    @Override
    public int totalEmp() {
        return empMapper.totalEmp();
    }
@Transactional
    @Override
    public int empupdate(Emp emp,Integer[] roleIds) {
        int count=empMapper.empupdate(emp);
        if(count>0){
            empMapper.deleteRoles(emp.getId());
            for (Integer roleId : roleIds) {
                empMapper.addEmpRole(emp.getId(),roleId);
            }
        }
        return count;
    }

    @Override
    public List<String> getAllEmpNo() {
        return empMapper.getAllEmpNo();
    }

}
