package com.juniordev.gradleapp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.beans.SearchCondition;

@Mapper
public interface MotorcycleMapper {

  /**
   * バイク情報を検索する
   * @param condition 検索条件
   * @return バイク情報リスト
   */
  public List<Motorcycle> selectByCondition(SearchCondition condition);
  
  
  /**
   * バイク情報をバイク番号で検索する
   * @param motoNo バイク番号
   * @return バイク情報
   */
  public Motorcycle selectByMotoNo(int motoNo);

  
  /**
   * バイク情報を更新する
   * @param before 更新前バイク情報
   * @return 更新件数
   */
  @Update("update motorcycle set moto_no = #{motoNo}, moto_name = #{motoName}, price = #{price}, comment = #{comment}, brand_id = #{brand.brandId}, create_date_time = #{createDateTime}, update_date_time = #{updateDateTime} where moto_no = #{motoNo}")
  public int update(Motorcycle before);
}
