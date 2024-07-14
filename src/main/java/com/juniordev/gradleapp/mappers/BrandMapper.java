package com.juniordev.gradleapp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.juniordev.gradleapp.beans.Brand;

@Mapper
public interface BrandMapper {
  
  /**
   * ブランド情報を全件検索する
   * @return ブランド情報リスト
   */
  public List<Brand> selectAll();
}
