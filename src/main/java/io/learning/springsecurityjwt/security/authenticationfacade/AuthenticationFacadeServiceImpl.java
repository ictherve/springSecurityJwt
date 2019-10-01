package io.learning.springsecurityjwt.security.authenticationfacade;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeServiceImpl implements AuthenticationFacadeService {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getAuthenticated() {
        return getAuthentication().getName();
    }

}
