package com.ssm.service;

import com.ssm.pojo.Depart;
import com.ssm.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    //    分页
    int getTotalCount(Map<String,Object> map);
    //    查询每页数据
    List<Student> getStudents(Map<String,Object> map);
    //    导出到Excel中的数据
    List<Student> getExcels(Map<String,Object> map);
    //批量新增
    int inserStudents(List<Student> s);
    //添加学生
    int insertStudent(Student student);
    //获取所有学生学号
    List<String> getAllNo();
    //获取学生人数
    int getStudentTotal();
}
