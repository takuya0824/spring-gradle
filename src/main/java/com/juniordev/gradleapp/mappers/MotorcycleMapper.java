package com.juniordev.gradleapp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.forms.SearchForm;

@Mapper
public interface MotorcycleMapper {

  /**
   * バイク情報を検索する
   * 
   * @param condition 検索条件
   * @return バイク情報リスト
   */
  public List<Motorcycle> selectByCondition(SearchForm condition);

  /**
   * バイク情報をバイク番号で検索する
   * 
   * @param motoNo バイク番号
   * @return バイク情報
   */
  public Motorcycle selectByMotoNo(int motoNo);

  /**
   * バイク情報を更新する
   * 
   * @param before 更新前バイク情報
   * @return 更新件数
   */
  @Update("update motorcycle set moto_no = #{motoNo}, moto_name = #{motoName}, price = #{price}, comment = #{comment}, brand_id = #{brand.brandId}, version = version + 1, create_date_time = #{createDateTime}, update_date_time = #{updateDateTime} where moto_no = #{motoNo} and version = #{version}")
  public int update(Motorcycle moto);

  /**
   * バイク情報を削除する
   * 
   * @param before 更新前バイク情報
   * @return 削除件数
   */
  @Delete("delete from motorcycle where moto_no = #{motoNo} and version = #{version}")
  public int delete(Motorcycle moto);

  /**
   * 新しいバイク番号を採番する
   * 
   * @return バイク番号
   */
  public Integer selectNewMotoNo();

  /**
   * バイク情報を登録する
   * 
   * @return 登録件数
   */
  public int insert(Motorcycle moto);
}
