package com.juniordev.gradleapp.beans;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

  // ブランドID
  @Min(value = 1, message="メーカーを選択してください。")
  private Integer brandId;
  // ブランド名
  private String brandName;
}
