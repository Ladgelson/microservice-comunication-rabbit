package br.com.cursoudemy.productapi.config.interceptor;

import br.com.cursoudemy.productapi.modules.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(isOptions(request)) return true;
        var token = request.getHeader(AUTHORIZATION);
        jwtService.validateAuthorization(token);
        return true;
    }

    private boolean isOptions(HttpServletRequest request) {
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }
}
