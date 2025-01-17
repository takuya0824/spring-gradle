package com.juniordev.gradleapp.beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {
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
  // 作成日時
  private LocalDateTime createDateTime;
  // 更新日時
  private LocalDateTime updateDateTime;
}
