package perillaleaves.shop.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import perillaleaves.shop.repository.TokenRepository;

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
