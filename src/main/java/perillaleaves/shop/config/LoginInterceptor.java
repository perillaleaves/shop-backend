package perillaleaves.shop.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import perillaleaves.shop.domain.user.Token;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.TokenRepository;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final TokenRepository tokenRepository;

    public LoginInterceptor(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        Token token = tokenRepository.findByToken(request.getHeader("X-Token"));
//        PrintWriter writer = response.getWriter();
//        if (token == null) {
//            writer.println("NotLogin");
//            return false;
//        }
//        writer.println(token);
//        return true;
//    }

}
