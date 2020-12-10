package com.ssm.service.Impl;

import com.ssm.mapper.DepartMapper;
import com.ssm.pojo.Depart;
import com.ssm.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {
       @Autowired
    DepartMapper departMapper;
    @Override
    public int addDepart(Depart depart) {
        return departMapper.addDepart(depart);
    }

    @Override
    public int getTotalCount() {
        return departMapper.getTotalCount();
    }

    @Override
    public List<Depart> getDeparts(long pageStrat, int pageSize) {
        return departMapper.getDeparts(pageStrat,pageSize);
    }

    @Override
    public int deleteDepart(int id) {
        return departMapper.deleteDepart(id);
    }

    @Override
    public int departUpdateById(Depart depart) {
        return departMapper.departUpdateById(depart);
    }

    @Override
    public List<Depart> findAllDeparts() {
        return departMapper.findAllDeparts();
    }

    @Override
    public Depart getDepartById(long id) {
        return departMapper.getDepartById(id);
    }

    @Override
    public int getDepartCountById(long id) {
        return departMapper.getDepartCountById(id);
    }
}
