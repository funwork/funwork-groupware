package io.funwork.config.commonconfig;

import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


/**
 * AJAX에서 cross domain 사용하기 위한 필터.
 * 배포시에는 도메인으로 제약을건다.
 *
 * @author changhwaoh ( changhwaoh.co@gmail.com )
 */
@Component
public class SimpleCorsFilter implements Filter {

  /**
   * Filter.
   *
   * @param req req
   * @param res res
   * @param chain chain
   * @throws IOException IOException
   * @throws ServletException ServletException
   */
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers",
        "origin, x-requested-with, content-type, accept");
    chain.doFilter(req, res);
  }

  public void init(FilterConfig filterConfig) {
  }

  public void destroy() {
  }

}
