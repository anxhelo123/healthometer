package al.ikubinfo.healthometer.users.service;

import al.ikubinfo.healthometer.users.dto.AuthDto;
import al.ikubinfo.healthometer.users.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private TokenProvider tokenProvider;
  private AuthenticationManagerBuilder authenticationManagerBuilder;

  public AuthDto getToken(AuthDto authDto) {
    try {
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
      Authentication authentication =
          authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      tokenProvider.createToken(authentication);
      authDto.setValid(true);
    } catch (Exception e) {
      authDto.setValid(false);
    }

    return authDto;
  }
}
