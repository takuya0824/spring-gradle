package com.juniordev.gradleapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.juniordev.gradleapp.beans.Brand;
import com.juniordev.gradleapp.beans.Motorcycle;

import lombok.extern.slf4j.Slf4j;

// @RestController
// @RequestMapping("/hello")
@Controller
@Slf4j
public class MotoController {

  // private static final Logger log = LoggerFactory.getLogger(HelloController.class);

  @GetMapping("")
  public String hello() {
    return "gradle hello -version2";
  }

  @GetMapping("/list")
  public String getList(Model model) {
    // ブランド
    List<Brand> brands = new ArrayList<>();
    brands.add(new Brand(1, "HONDA"));
    brands.add(new Brand(2, "KAWASAKI"));
    
    // バイク
    List<Motorcycle> motos = new ArrayList<>();
    motos.add(new Motorcycle(1, "GB350", 3500000, "かっこいい", new Brand(1, "HONDA"), null, null));
    motos.add(new Motorcycle(2, "FG230", 2000000, "派手", new Brand(2, "KAWASAKI"), null, null));
    motos.add(new Motorcycle(3, "SG260", 5000000, "シック", new Brand(2, "KAWASAKI"), null, null));

    model.addAttribute("brands", brands);
    model.addAttribute("motos", motos);

    log.debug("motos: {}", motos);


    return "list";
  }
}
