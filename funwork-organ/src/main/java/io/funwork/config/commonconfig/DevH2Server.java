package io.funwork.config.commonconfig;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 개발모드에서만 실행되는 h2 관련 설정파일.
 *
 * @author changhwaoh ( changhwaoh.co@gmail.com )
 */
@Configuration
public class DevH2Server {

  /**
   * h2server 데이터베이스 관리도구 실행.
   *
   * @author changhwaoh ( changhwaoh.co@gmail.com )
   */
  @Bean
  //@Profile("dev")
  public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
    registration.addUrlMappings("/console/*");
    return registration;
  }
}
