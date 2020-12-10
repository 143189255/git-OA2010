package com.ssm.service.Impl;

import com.ssm.mapper.MajorMapper;
import com.ssm.pojo.Major;
import com.ssm.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
     private MajorMapper majorMapper;
    @Override
    public List<Major> getMajors() {
        return majorMapper.getMajors();
    }
}
