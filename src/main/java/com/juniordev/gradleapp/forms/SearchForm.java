package com.juniordev.gradleapp.forms;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {

  private Integer brandId;

  @Size(min = 2, max = 10)
  private String keyword;
}
