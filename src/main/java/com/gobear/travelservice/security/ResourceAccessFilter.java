package com.gobear.travelservice.security;

import com.gobear.travelservice.utils.TokenUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceAccessFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String username = TokenUtils.getSubject(httpServletRequest);
        System.out.println("Filter: username = " + username);
        if (username == null) {
            String authService = this.getFilterConfig().getInitParameter("services.auth");
            System.out.println("Your request need to authenticate, redirect to: " + authService);
            httpServletResponse.addCookie(new Cookie("redirectUrl", httpServletRequest.getRequestURL().toString()));
            httpServletResponse.sendRedirect(authService);
//            httpServletResponse.sendRedirect(authService + "?redirect=" + httpServletRequest.getRequestURL());
        } else {
            httpServletRequest.setAttribute("username", username);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
