package org.voduybao.bookstorebackend.tools.security.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.voduybao.bookstorebackend.dao.entities.user.User;
import org.voduybao.bookstorebackend.dao.repositories.user.UserRepository;
import org.voduybao.bookstorebackend.services.auth.JwtService;
import org.voduybao.bookstorebackend.tools.contants.a.AdminRequired;
import org.voduybao.bookstorebackend.tools.contants.e.RoleEnum;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Setter(onMethod_ = @Autowired)
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            boolean methodHasAnnotation = handlerMethod.hasMethodAnnotation(AdminRequired.class);
            boolean classHasAnnotation = handlerMethod.getBeanType().isAnnotationPresent(AdminRequired.class);
            if (methodHasAnnotation || classHasAnnotation) {
                String authHeader = request.getHeader("Authorization");
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    throw new ResponseException(ResponseErrors.NOT_AUTHORIZED_ACTION);
                }
                String token = authHeader.substring(7);
                String email = jwtService.extractAccessTokenEmail(token);
                User user = userRepository.findUserByEmail(email)
                        .orElseThrow(() -> new ResponseException(ResponseErrors.ACCOUNT_EXISTS));

                boolean isAdmin = user.getRoles().stream()
                        .anyMatch(role -> role.getRoleName() == RoleEnum.ADMIN);

                if (!isAdmin) {
                    throw new ResponseException(ResponseErrors.NOT_AUTHORIZED_ACTION);
                }
                return true;
            }
        }
        return true;
    }
}