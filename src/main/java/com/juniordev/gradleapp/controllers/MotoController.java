package com.juniordev.gradleapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.juniordev.gradleapp.beans.Brand;
import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.beans.SearchForm;
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
  public String getList(SearchForm searchForm, Model model) {
    
    this.setBrands(model);

    // バイク
    List<Motorcycle> motos = motoService.getMotos(searchForm);
    model.addAttribute("motos", motos);

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
   * ブランドリストをmodelにセットする
   * @param model model
   */
  private void setBrands(Model model) {
    // ブランドリスト取得
    List<Brand> brands = motoService.getBrands();
    model.addAttribute("brands", brands);
  }
}
