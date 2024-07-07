package com.juniordev.gradleapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

  // ブランドID
  private Integer brandId;
  // ブランド名
  private String brandName;
}
