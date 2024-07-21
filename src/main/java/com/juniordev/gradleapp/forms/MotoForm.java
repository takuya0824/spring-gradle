package com.juniordev.gradleapp.forms;

import com.juniordev.gradleapp.beans.Brand;

import lombok.Data;

@Data
public class MotoForm {
  // バイク番号
  private Integer motoNo;
  // バイク名
  private String motoName;
  // 価格
  private Integer price;
  // コメント
  private String comment;
  // ブランド
  private Brand brand;
  // バージョン
  private Integer version;
}
