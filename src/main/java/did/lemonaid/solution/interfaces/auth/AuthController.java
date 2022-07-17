package did.lemonaid.solution.interfaces.auth;

import did.lemonaid.solution.application.auth.AuthFacade;
import did.lemonaid.solution.security.token.JwtRequestFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name="Authentication", description = "Authentication API")
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Slf4j
public class AuthController {
  private final AuthFacade authFacade;
  private final AuthDtoMapper authDtoMapper;


  @Operation(summary = "Login")
  @PostMapping("/login")
  public ResponseEntity<AuthDto.LogInResponse> logIn(@Valid @RequestBody  AuthDto.LogInRequest request) {

    var login = authDtoMapper.of(request);
    var jwt = authFacade.logIn(login);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtRequestFilter.AUTHORIZATION_HEADER,"Bearer "+jwt);
    return new ResponseEntity<>(AuthDto.LogInResponse.builder().token(jwt).build(), httpHeaders, HttpStatus.OK);
  }

}

