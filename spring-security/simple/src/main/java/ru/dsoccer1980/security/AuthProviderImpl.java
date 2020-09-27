package ru.dsoccer1980.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.dsoccer1980.model.User;
import ru.dsoccer1980.repository.UserRepository;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

  private final UserRepository userRepository;

  public AuthProviderImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String username = authentication.getName();
    User user = userRepository.findByName(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    String password = authentication.getCredentials().toString();
    if (!password.equals(user.getPassword())) {
      throw new BadCredentialsException("Invalid credentials");
    }
    List<GrantedAuthority> authorities = new ArrayList<>();
    return new UsernamePasswordAuthenticationToken(username, null, authorities);
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(UsernamePasswordAuthenticationToken.class);
  }
}
