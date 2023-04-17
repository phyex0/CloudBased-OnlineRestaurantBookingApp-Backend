package com.upspoon.gateway.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;
import java.util.*;

/**
 * @author burak.yesildal
 */
@Slf4j
@AllArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        if (Objects.isNull(authentication) || Objects.isNull(targetDomainObject) || Objects.isNull(permission)) {
            log.debug("Authentication or TargetDomainObject or Permission is null");
            return false;
        }

        HttpServletRequest currentRequest = getCurrentRequest();

        if (Objects.isNull(currentRequest)) {
            log.debug("Current Request is null");
            return false;
        }

        Map<String, Object> claims = ((JwtAuthenticationToken) authentication).getToken().getClaims();
        List<String> roles = new ArrayList<>();

        if (claims.containsKey("roles"))
            roles.addAll((Collection<? extends String>) claims.get("roles"));
        return roles.contains(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }


    private HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (Objects.isNull(requestAttributes)) {
            log.debug("requestAttributes is null");
            return null;
        }

        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
}
