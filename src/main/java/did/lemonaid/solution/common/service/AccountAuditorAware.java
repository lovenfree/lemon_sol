package did.lemonaid.solution.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AccountAuditorAware implements AuditorAware<String>{

  @Override
  public Optional<String> getCurrentAuditor() {

    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if(authentication == null){
     log.info("Security Context에 인증 정보가 없습니다.");
      return Optional.of(UUID.randomUUID().toString());
    }

    String userId = null;
    if(authentication.getPrincipal() instanceof UserDetails){

      System.out.println("?????");
      UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
      userId = springSecurityUser.getUsername();
    }else if(authentication.getPrincipal() instanceof String){
      System.out.println("?????");
      userId = (String) authentication.getPrincipal();
    }
    System.out.println(userId);
    return Optional.ofNullable(userId);
  }
}
