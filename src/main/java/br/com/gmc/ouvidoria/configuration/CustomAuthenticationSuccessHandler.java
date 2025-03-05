package br.com.gmc.ouvidoria.configuration;

import br.com.gmc.ouvidoria.entity.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authentication Authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        if(user.isMustUpdatePassword()) {
            response.sendRedirect("/users/changepwd");
        }
        else {
            response.sendRedirect("/inicio");
        }
    }
}
