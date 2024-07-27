package com.juniordev.gradleapp.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.juniordev.gradleapp.beans.Brand;
import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.beans.SearchForm;
import com.juniordev.gradleapp.forms.MotoForm;
import com.juniordev.gradleapp.services.MotoService;

import lombok.extern.slf4j.Slf4j;

// @RestController
// @RequestMapping("/hello")
@Controller
@Slf4j
public class MotoController {

  // private static final Logger log = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  MotoService motoService;

  @GetMapping("")
  public String hello() {
    return "gradle hello -version2";
  }

  /**
   * バイク一覧を検索する
   * @param searchForm 検索条件
   * @param model model
   * @return 遷移先
   */
  @GetMapping("/list")
  public String getList(@Validated SearchForm searchForm, BindingResult result, Model model) {
    
    if(result.hasErrors()) {
      return "list";
    }
    this.setBrands(model);

    // バイク
    List<Motorcycle> motos = motoService.getMotos(searchForm);
    model.addAttribute("motos", motos);
    model.addAttribute("datetime", LocalDateTime.now());

    log.debug("motos: {}", motos);

    return "list";
  }

  /**
   * 検索条件をクリアする
   * @param searchForm 検索条件
   * @param model model
   * @return 遷移先
   */
  @GetMapping("/list/reset")
  public String reset(SearchForm searchForm, Model model) {
    this.setBrands(model);

    searchForm = new SearchForm();
    return "list";
  }

  /**
   * 更新画面の初期表示
   * @param motoNo バイク番号
   * @param motoForm 入力内容
   * @param model Model
   * @return 遷移先
   */
  @GetMapping("/list/{motoNo}")
  public String initUpdate(@PathVariable("motoNo") Integer motoNo, @ModelAttribute MotoForm motoForm, Model model) {
    this.setBrands(model);

    Motorcycle moto = motoService.getMotos(motoNo);

    BeanUtils.copyProperties(moto, motoForm);
    return "moto";
  }

  /**
   * 登録画面の初期表示
   * @param motoForm 入力内容
   * @param model Model
   * @return 遷移先
   */
  @GetMapping("/list/new")
  public String initNew(@ModelAttribute MotoForm motoForm, Model model) {
    this.setBrands(model);

    return "moto";
  }

  /**
   * バイク情報を保存する
   * @param motoForm 入力内容
   * @param result BindingResult
   * @param model Model
   * @return 遷移先
   */
  @SuppressWarnings("null")
  @PostMapping("/list/save")
  public String save(@ModelAttribute MotoForm motoForm, BindingResult result, Model model) {
    try {
      log.info("motoForm:{}", motoForm);
      Motorcycle moto = new Motorcycle();
      BeanUtils.copyProperties(motoForm, moto);
      int count = motoService.save(moto);
      log.info("{}件更新", count);
  
      return "redirect:/list";
    } catch (OptimisticLockingFailureException e) {
      this.setBrands(model);
      result.addError(new ObjectError("global", e.getMessage()));
      return "moto";
    }
  }

  /**
   * ブランドリストをmodelにセットする
   * @param model model
   */
  private void setBrands(Model model) {
    // ブランドリスト取得
    List<Brand> brands = motoService.getBrands();
    model.addAttribute("brands", brands);
  }

  /**
   * バイク情報を削除する
   * @param motoForm 入力内容
   * @param result BindingResult
   * @param model Model
   * @return 遷移先
   */
  @SuppressWarnings("null")
  @PostMapping("/list/delete")
  public String delete(@ModelAttribute MotoForm motoForm, BindingResult result, Model model) {
    try {
      log.info("motoForm:{}", motoForm);
      Motorcycle moto = new Motorcycle();
      BeanUtils.copyProperties(motoForm, moto);
      int count = motoService.delete(moto);
      log.info("{}件削除", count);
  
      return "redirect:/list";
    } catch (OptimisticLockingFailureException e) {
      this.setBrands(model);
      result.addError(new ObjectError("global", e.getMessage()));
      return "moto";
    }
  }
}
