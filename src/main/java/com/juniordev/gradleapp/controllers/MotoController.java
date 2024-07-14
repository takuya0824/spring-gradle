package com.juniordev.gradleapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.juniordev.gradleapp.beans.Brand;
import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.beans.SearchCondition;
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

  @GetMapping("/list")
  public String getList(Model model) {
    
    // ブランド
    List<Brand> brands = motoService.getBrands();
    
    // バイク
    SearchCondition condition = new SearchCondition();
    List<Motorcycle> motos = motoService.getMotos(condition);

    model.addAttribute("brands", brands);
    model.addAttribute("motos", motos);

    log.debug("motos: {}", motos);


    return "list";
  }
}
