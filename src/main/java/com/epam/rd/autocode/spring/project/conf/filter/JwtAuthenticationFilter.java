package com.epam.rd.autocode.spring.project.conf.filter;

import com.epam.rd.autocode.spring.project.model.RefreshToken;
import com.epam.rd.autocode.spring.project.model.UserPrincipal;
import com.epam.rd.autocode.spring.project.repo.RefreshTokenRepository;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import com.epam.rd.autocode.spring.project.service.authService.RefreshTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String jwt = null;

        String authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER_PREFIX)) {
            jwt = authHeader.substring(BEARER_PREFIX.length());
        }

        else if (request.getCookies() != null) {
            for (var cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
        if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = null;

            try {
                username = jwtService.extractEmailName(jwt);
            } catch (Exception e) {
                handleTokenRefresh(request, response);
                response.sendRedirect(request.getRequestURI());
                return;
            }

            if (StringUtils.hasText(username)) {
                try {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtService.isTokenValid(jwt, userDetails) && userDetails.isEnabled()) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } else if (!userDetails.isEnabled()) {
                        throw new UsernameNotFoundException("User is not enabled");
                    }
                } catch (UsernameNotFoundException ex) {
                    if (request.getCookies() != null) {
                        for (var cookie : request.getCookies()) {
                            if ("jwt".equals(cookie.getName())) {
                                cookie.setValue(null);
                                cookie.setPath("/");
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                                break;
                            }
                        }
                    }
                    response.sendRedirect("/auth/login");
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void handleTokenRefresh(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh_token".equals(cookie.getName())) {
                    String refreshTokenString = cookie.getValue();

                    refreshTokenRepository.findByToken(refreshTokenString)
                            .map(refreshTokenService::verifyExpiration)
                            .map(RefreshToken::getUser)
                            .ifPresent(user -> {
                                String newAccessToken = jwtService.generateToken(new UserPrincipal(user));
                                Cookie accessCookie = new Cookie("jwt", newAccessToken);
                                accessCookie.setHttpOnly(true);
                                accessCookie.setPath("/");
                                accessCookie.setMaxAge(15 * 60);
                                response.addCookie(accessCookie);
                            });
                }
            }
        }
    }
}

