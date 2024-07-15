package com.juniordev.gradleapp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.juniordev.gradleapp.beans.Motorcycle;
import com.juniordev.gradleapp.beans.SearchForm;

@SpringBootTest
public class MotoServiceTest {

  DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm");
  
  @Autowired
  MotoService motoService;

  // @Test
  // void バイク情報を全件検索できる() {
  //   SearchCondition condition = new SearchCondition();
  //   List<Motorcycle> motos = motoService.getMotos(condition);

  //   assertThat(motos.size()).isEqualTo(4);

  //   Motorcycle moto = motos.get(0);
  //   assertThat(moto.getMotoNo()).isEqualTo(1);
  //   assertThat(moto.getMotoName()).isEqualTo("GB350");
  //   assertThat(moto.getPrice()).isEqualTo(3500000);
  //   assertThat(moto.getComment()).isEqualTo("かっこいい");
  //   assertThat(moto.getBrand().getBrandName()).isEqualTo("HONDA");
  // }

  // @Test
  // void ブランド情報を全件検索できる() {
  //   List<Brand> brands = motoService.getBrands();

  //   assertThat(brands.size()).isEqualTo(4);

  //   Brand brand = brands.get(0);
  //   assertThat(brand.getBrandId()).isEqualTo(1);
  //   assertThat(brand.getBrandName()).isEqualTo("HONDA");
  // }

  @DisplayName("バイク一覧取得 条件：ブランドID")
  @ParameterizedTest
  @CsvSource({"1, HONDA", "2, KAWASAKI", "3, SUZUKI", "4, NISSAN"})
  void test001(int brandId, String brandName) {
    SearchForm condition = new SearchForm();
    condition.setBrandId(brandId);
    List<Motorcycle> motos = motoService.getMotos(condition);
    assertThat(motos.size()).isGreaterThanOrEqualTo(1);
    for (Motorcycle moto : motos) {
      assertThat(moto.getBrand().getBrandName()).isEqualTo(brandName);
    }
  }

  @DisplayName("バイク一覧取得 条件：ブランドID 該当なし")
  @Test
  void test002() {
    SearchForm condition = new SearchForm();
    condition.setBrandId(99);
    List<Motorcycle> motos = motoService.getMotos(condition);
    assertThat(motos.size()).isEqualTo(0);
  }

  @DisplayName("バイク一覧取得 条件：バイク名 完全一致")
  @ParameterizedTest
  @CsvSource({"GB350", "FG230", "SG260"})
  void test003(String motoName) {
    SearchForm condition = new SearchForm();
    condition.setKeyword(motoName);
    List<Motorcycle> motos = motoService.getMotos(condition);
    assertThat(motos.size()).isGreaterThanOrEqualTo(1);
    for (Motorcycle moto : motos) {
      assertThat(moto.getMotoName()).isEqualTo(motoName);
    }
  }

  @DisplayName("バイク一覧取得 条件：バイク名 部分一致")
  @ParameterizedTest
  @CsvSource({"GB, GB350", "230, FG230", "G26, SG260"})
  void test004(String keyword, String motoName) {
    SearchForm condition = new SearchForm();
    condition.setKeyword(keyword);

    List<Motorcycle> motos = motoService.getMotos(condition);
    assertThat(motos.size()).isGreaterThanOrEqualTo(1);
    for (Motorcycle moto : motos) {
      assertThat(moto.getMotoName()).isEqualTo(motoName);
    }
  }

  @DisplayName("バイク一覧取得 条件：バイク名 該当なし")
  @Test
  void test005() {
    SearchForm condition = new SearchForm();
    condition.setKeyword("存在しないバイク名");
    List<Motorcycle> motos = motoService.getMotos(condition);

    assertThat(motos.size()).isEqualTo(0);
  }

  @DisplayName("バイク一覧取得 条件：ブランドID, バイク名")
  @ParameterizedTest
  @CsvSource({"1, GB, GB350", "2, 230, FG230", "3, G2, SG260"})
  void test006(int brandId, String keyword, String motoName) {
    SearchForm condition = new SearchForm();
    condition.setBrandId(brandId);
    condition.setKeyword(keyword);

    List<Motorcycle> motos = motoService.getMotos(condition);

    assertThat(motos.size()).isGreaterThanOrEqualTo(1);
    for (Motorcycle moto : motos) {
      assertThat(moto.getMotoName()).startsWith(motoName);
    }
  }

  @DisplayName("バイク一覧取得 条件：ブランドID, バイク名 該当なし")
  @ParameterizedTest
  @CsvSource({"1, GC350", "2, KG230", "3, SR260"})
  void test007(int brandId, String keyword) {
    SearchForm condition = new SearchForm();
    condition.setBrandId(brandId);
    condition.setKeyword(keyword);

    List<Motorcycle> motos = motoService.getMotos(condition);

    assertThat(motos.size()).isEqualTo(0);
  }

  @DisplayName("バイク一覧取得 条件：なし 全件該当")
  @Test
  void test008() {
    SearchForm condition = new SearchForm();

    List<Motorcycle> motos = motoService.getMotos(condition);

    assertThat(motos.size()).isEqualTo(4);
  }

  @DisplayName("バイク情報取得 条件：バイク番号")
  @ParameterizedTest
  @CsvSource({"1, GB350", "2, FG230", "3, SG260"})
  void test009(int motoNo, String motoName) {
    Motorcycle moto = motoService.getMotos(motoNo);

    assertThat(moto.getMotoName()).isEqualTo(motoName);
  }

  @DisplayName("バイク情報取得 条件：バイク番号 全項目確認")
  @Test
  void test010() {
    Motorcycle moto = motoService.getMotos(1);

    assertThat(moto.getMotoNo()).isEqualTo(1);
    assertThat(moto.getMotoName()).isEqualTo("GB350");
    assertThat(moto.getPrice()).isEqualTo(3500000);
    assertThat(moto.getComment()).isEqualTo("かっこいい");
    assertThat(moto.getBrand().getBrandId()).isEqualTo(1);
    assertThat(moto.getCreateDateTime().format(dtFormatter)).isEqualTo(LocalDateTime.now().format(dtFormatter));
    assertThat(moto.getUpdateDateTime()).isNull();
  }

  @DisplayName("バイク情報更新")
  @Test
  @Transactional
  void test011() {
    Motorcycle before = motoService.getMotos(1);
    before.setMotoName("newname");


    motoService.save(before);

    Motorcycle after = motoService.getMotos(1);
    assertThat(after.getMotoName()).isEqualTo("newname");
  }
}
