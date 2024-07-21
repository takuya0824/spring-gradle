package com.juniordev.gradleapp.services;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juniordev.gradleapp.beans.Brand;
import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.beans.SearchForm;
import com.juniordev.gradleapp.mappers.BrandMapper;
import com.juniordev.gradleapp.mappers.MotorcycleMapper;

@Service
public class MotoService {

  @Autowired
  MessageSource messageSource;
  
  @Autowired
  MotorcycleMapper motorcycleMapper;

  @Autowired
  BrandMapper brandMapper;
  
  @Transactional
  public List<Motorcycle> getMotos(SearchForm condition) {
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

  /**
   * バイク情報を更新する。
   * @param moto バイク情報
   * @return 更新件数
   */
  @Transactional
  public int save(Motorcycle moto) {
    int count =  motorcycleMapper.update(moto);
    if (count == 0) {
      throw new OptimisticLockingFailureException(messageSource.getMessage("error.OptimisticLockingFailure", null, Locale.JAPANESE));
    }
    if (count > 1) {
      throw new RuntimeException(messageSource.getMessage("error.Runtime", null, Locale.JAPANESE));
    }
    return count;
  }
}