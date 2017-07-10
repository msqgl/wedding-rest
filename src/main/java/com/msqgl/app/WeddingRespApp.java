package com.msqgl.app;

import com.msqgl.app.config.WebConfig;
import com.msqgl.app.service.WeddingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.msqgl.app"})
public class WeddingRespApp {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(WeddingRespApp.class);
    new WebConfig(ctx.getBean(WeddingService.class));
    ctx.registerShutdownHook();
  }

}
