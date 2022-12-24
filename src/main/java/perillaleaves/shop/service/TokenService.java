package perillaleaves.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.community.domain.Token;
import perillaleaves.community.repository.TokenRepository;

@Transactional
@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token findByAccessToken(String token) {

        return tokenRepository.findByToken(token);
    }

    public void deleteToken(String token) {

        tokenRepository.deleteByToken(token);
    }

}

