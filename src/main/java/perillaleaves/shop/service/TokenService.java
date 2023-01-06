package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.user.Token;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.TokenRepository;

import java.util.Optional;

@Transactional
@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public void deleteToken(String token) {
        if (token.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        Optional<Token> findToken = Optional.ofNullable(tokenRepository.findByToken(token));
        if (findToken.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        tokenRepository.deleteByToken(token);
    }

}

