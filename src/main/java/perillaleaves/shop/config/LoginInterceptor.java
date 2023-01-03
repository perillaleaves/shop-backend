package perillaleaves.shop.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import perillaleaves.shop.domain.user.Token;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.TokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final TokenRepository tokenRepository;

    public LoginInterceptor(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public boolean preHandle(String accessToken) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인한 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인한 유저가 아닙니다.");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
    }
}
