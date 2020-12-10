package com.ssm.service.Impl;

import com.ssm.mapper.StudentMapper;
import com.ssm.pojo.Depart;
import com.ssm.pojo.Student;
import com.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public int getTotalCount(Map<String, Object> map) {
        return studentMapper.getTotalCount(map);
    }

    @Override
    public List<Student> getStudents(Map<String, Object> map) {
        return studentMapper.getStudents(map);
    }

    @Override
    public List<Student> getExcels(Map<String, Object> map) {
        return studentMapper.getExcels(map);
    }

    @Override
    public int inserStudents(List<Student> s) {
        return studentMapper.inserStudents(s);
    }

    @Override
    public int insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public List<String> getAllNo() {
        return studentMapper.getAllNo();
    }

    @Override
    public int getStudentTotal() {
        return studentMapper.getStudentTotal();
    }
}
