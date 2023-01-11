package com.tencent.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tencent.mall.mapper")
public class WxCloudRunApplication {

  public static void main(String[] args) {
    SpringApplication.run(WxCloudRunApplication.class, args);
  }

}
