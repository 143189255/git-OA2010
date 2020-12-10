package com.ssm.service.Impl;

import com.ssm.mapper.TClassMapper;
import com.ssm.pojo.TClass;
import com.ssm.service.TClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TClassServiceImpl implements TClassService {
    @Autowired
    private TClassMapper tClassMapper;
    @Override
    public List<TClass> getClasses() {
        return tClassMapper.getClasses();
    }
    @Override
    public int addClass(TClass t) {
        return tClassMapper.addClass(t);
    }

    @Override
    public List<TClass> getAllClass() {
        return tClassMapper.getAllClass();
    }

    @Override
    public int getTotalCount() {
        return tClassMapper.getTotalCount();
    }

}
