package com.juniordev.gradleapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniordev.gradleapp.beans.Brand;
import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.mappers.BrandMapper;
import com.juniordev.gradleapp.mappers.MotorcycleMapper;

@Service
public class MotoService {
  
  @Autowired
  MotorcycleMapper motorcycleMapper;

  @Autowired
  BrandMapper brandMapper;
  
  public List<Motorcycle> getMotos() {
    return motorcycleMapper.selectAll();
  }

  public List<Brand> getBrands() {
    return brandMapper.selectAll();
  }
}