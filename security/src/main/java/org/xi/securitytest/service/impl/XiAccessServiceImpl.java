package org.xi.securitytest.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("xiAccessService")
public class XiAccessServiceImpl implements XiAccessService {
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            return user.getAuthorities().contains(new SimpleGrantedAuthority(request.getRequestURI()));
        }
        return false;
    }
}
