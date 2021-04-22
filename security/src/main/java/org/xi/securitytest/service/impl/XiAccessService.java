package org.xi.securitytest.service.impl;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface XiAccessService {

    boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication);
}
