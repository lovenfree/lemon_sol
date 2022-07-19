package did.lemonaid.solution.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import did.lemonaid.solution.security.filter.RestLoginProcessingFilter;
import did.lemonaid.solution.security.handler.CustomAuthenticationFailureHandler;
import did.lemonaid.solution.security.handler.CustomAuthenticationSuccessHandler;
import did.lemonaid.solution.security.service.CustomUserDetailsService;
import did.lemonaid.solution.security.token.*;
import did.lemonaid.solution.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomUserDetailsService userDetailsService;
  private final JwtTokenProvider tokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  //private final JwtRequestFilter jwtRequestFilter;
  private final JwtExceptionFilter jwtExceptionFilter;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final ObjectMapper objectMapper;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public RestLoginProcessingFilter restLoginProcessingFilter() throws Exception{
    RestLoginProcessingFilter restLoginProcessingFilter = new RestLoginProcessingFilter();
    restLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());

    restLoginProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
    restLoginProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
    return restLoginProcessingFilter;
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new CustomAuthenticationSuccessHandler(objectMapper, userDetailsService, tokenProvider);
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new CustomAuthenticationFailureHandler(objectMapper);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    return new CustomAuthenticationProvider(userDetailsService);
  }
  @Override
  public void configure(WebSecurity web) throws Exception{
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors()
      .and()
      .csrf().disable();
//      .formLogin().disable()


    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http
      .authorizeRequests()
      .antMatchers("/**").permitAll()
      .anyRequest().authenticated()
        .and()
      .exceptionHandling()
      .authenticationEntryPoint(jwtAuthenticationEntryPoint)
      .accessDeniedHandler(jwtAccessDeniedHandler);

    http
      .addFilterBefore(restLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(new JwtRequestFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(jwtExceptionFilter, JwtRequestFilter.class);
//      .apply(new JwtSecurityConfig(tokenProvider));
    http.httpBasic().disable();
  }
}
