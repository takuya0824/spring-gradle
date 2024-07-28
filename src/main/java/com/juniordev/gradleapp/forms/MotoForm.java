package com.juniordev.gradleapp.forms;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.juniordev.gradleapp.beans.Brand;

import lombok.Data;

@Data
public class MotoForm {
  // バイク番号
  private Integer motoNo;
  // バイク名
  @NotBlank
  @Size(min = 1, max = 50)
  private String motoName;
  // 価格
  @Min(100000)
  private Integer price;
  // コメント
  @Size(max = 200)
  private String comment;
  // ブランド
  @Valid
  private Brand brand;
  // バージョン
  private Integer version;
}
