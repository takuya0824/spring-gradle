package com.juniordev.gradleapp.commons;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * リクエストの空白文字をnullに変換する共通処理
 */
@ControllerAdvice
public class CommonAdvice {
  
  @InitBinder
  public void InitBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }
}
