package com.juniordev.gradleapp.services;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.juniordev.gradleapp.beans.Brand;
import com.juniordev.gradleapp.beans.Motorcycle;

@SpringBootTest
public class MotoServiceTest {
  
  @Autowired
  MotoService motoService;

  @Test
  void バイク情報を全件検索できる() {
    List<Motorcycle> motos = motoService.getMotos();

    assertThat(motos.size()).isEqualTo(4);

    Motorcycle moto = motos.get(0);
    assertThat(moto.getMotoNo()).isEqualTo(1);
    assertThat(moto.getMotoName()).isEqualTo("GB350");
    assertThat(moto.getPrice()).isEqualTo(3500000);
    assertThat(moto.getComment()).isEqualTo("かっこいい");
    assertThat(moto.getBrand().getBrandName()).isEqualTo("HONDA");
  }

  @Test
  void ブランド情報を全件検索できる() {
    List<Brand> brands = motoService.getBrands();

    assertThat(brands.size()).isEqualTo(4);

    Brand brand = brands.get(0);
    assertThat(brand.getBrandId()).isEqualTo(1);
    assertThat(brand.getBrandName()).isEqualTo("HONDA");
  }
}
