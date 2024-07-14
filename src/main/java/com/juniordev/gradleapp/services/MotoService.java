package com.juniordev.gradleapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juniordev.gradleapp.beans.Brand;
import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.beans.SearchCondition;
import com.juniordev.gradleapp.mappers.BrandMapper;
import com.juniordev.gradleapp.mappers.MotorcycleMapper;

@Service
public class MotoService {
  
  @Autowired
  MotorcycleMapper motorcycleMapper;

  @Autowired
  BrandMapper brandMapper;
  
  @Transactional
  public List<Motorcycle> getMotos(SearchCondition condition) {
    return motorcycleMapper.selectByCondition(condition);
  }

  @Transactional
  public Motorcycle getMotos(int motoNo) {
    return motorcycleMapper.selectByMotoNo(motoNo);
  }

  @Transactional
  public List<Brand> getBrands() {
    return brandMapper.selectAll();
  }

  @Transactional
  public int save(Motorcycle before) {
    return motorcycleMapper.update(before);
  }
}