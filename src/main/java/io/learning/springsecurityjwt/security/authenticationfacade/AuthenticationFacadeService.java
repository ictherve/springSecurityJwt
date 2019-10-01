package io.learning.springsecurityjwt.security.authenticationfacade;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacadeService {

    Authentication getAuthentication();

    String getAuthenticated();
}
