package did.lemonaid.solution.interfaces.auth;

import did.lemonaid.solution.security.filter.AuthDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name="Authentication", description = "Authentication API")
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Slf4j
public class AuthController {


  @Operation(summary = "Login")
  @PostMapping("/login")
  public ResponseEntity<AuthDto.LogInResponse> logIn(@Valid @RequestBody  AuthDto.LogInRequest request) {

//    var login = authDtoMapper.of(request);
//    var jwt = authFacade.logIn(login);
//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.add(JwtRequestFilter.AUTHORIZATION_HEADER,"Bearer "+jwt);
//    return new ResponseEntity<>(AuthDto.LogInResponse.builder().token(jwt).build(), httpHeaders, HttpStatus.OK);
    return null;
  }

}

