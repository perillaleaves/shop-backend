package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.common.HexConverter;
import perillaleaves.shop.config.EncryptUtils;
import perillaleaves.shop.domain.enumList.Role;
import perillaleaves.shop.domain.user.Token;
import perillaleaves.shop.domain.user.User;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.TokenRepository;
import perillaleaves.shop.repository.UserRepository;
import perillaleaves.shop.request.user.UserDTO;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public User save(UserDTO userDTO) {
        validate(userDTO);
        User user = mapper(userDTO);

        return userRepository.save(user);
    }

    public User findByLoginIdOrNull(String login_id) {
        findByLoginIdValidate(login_id);

        return userRepository.findByLoginId(login_id).orElse(null);
    }

    public User findByPhoneNumberOrNull(String phone_number) {
        findByPhoneNumberValidate(phone_number);

        return userRepository.findByPhoneNumber(phone_number).orElse(null);
    }

    public User findByEmailOrNull(String email) {
        findByEmailValidate(email);

        return userRepository.findByEmail(email).orElse(null);
    }

    public User findByNameAndPhoneNumber(String name, String phone_number) {
        findByNameAndPhoneNumberValidate(name, phone_number);

        return userRepository.findByNameAndPhoneNumber(name, phone_number).orElse(null);
    }

    public User findByLoginIdAndNameAndPhoneNumber(String login_id, String name, String phone_number) {
        findByLoginIdAndNameAndPhoneNumberValidate(login_id, name, phone_number);

        return userRepository.findByLoginIdAndNameAndPhoneNumber(login_id, name, phone_number).orElse(null);
    }

    public void updateByPassword(String login_id, String name, String phone_number, String password) {
        updateByPasswordValidate(login_id, name, phone_number, password);
    }

    public String login(String login_id, String password) throws UnsupportedEncodingException {
        User user = userRepository.findByLoginId(login_id).orElse(null);
        if (user == null) {
            throw new APIError("InconsistencyLoginId", "아이디가 일치하지 않습니다.");
        }
        if (!user.getPassword().equals(EncryptUtils.sha256(password))) {
            throw new APIError("InconsistencyPassword", "비밀번호가 일치하지 않습니다.");
        }

        Token token = new Token(user.getId(), generateToken(login_id));

        tokenRepository.save(token);
        return token.getToken();
    }


    public User userInformation(String accessToken) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        return userRepository.findById(token.get().getUser_id()).get();
    }

    public void userUpdate(String accessToken, String password, String phone_number, String email) {
        userUpdateValidate(accessToken, password, phone_number, email);
    }

    private void validate(UserDTO userDTO) {
        boolean password_validate = Pattern.matches("^(?=.*?[A-Z]+).{8,}", userDTO.getPassword());
        boolean email_validate = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", userDTO.getEmail());

        if (userDTO.getLogin_id().isBlank()) {
            throw new APIError("EmptyId", "아이디를 입력해주세요.");
        }
        if (userDTO.getLogin_id().length() < 8) {
            throw new APIError("LengthId", "아이디를 8글자 이상 입력해주세요.");
        }
        if (userDTO.getPassword().isBlank()) {
            throw new APIError("EmptyPassword", "비밀번호를 입력해주세요.");
        }
        if (userDTO.getPassword().length() < 8) {
            throw new APIError("LengthPassword", "비밀번호를 8글자 이상 입력해주세요.");
        }
        if (!password_validate) {
            throw new APIError("FormPassword", "비밀번호를 양식에 맞게 입력해주세요.");
        }
        if (userDTO.getName().isBlank()) {
            throw new APIError("EmptyName", "이름을 입력해주세요.");
        }
        if (userDTO.getPhone_number().isBlank()) {
            throw new APIError("EmptyPhoneNumber", "연락처를 입력해주세요.");
        }
        if (userDTO.getEmail().isBlank()) {
            throw new APIError("EmptyEmail", "이메일을 입력해주세요.");
        }
        if (!email_validate) {
            throw new APIError("FormEmail", "이메일을 양식에 맞게 입력해주세요.");
        }

        Optional<User> userByLoginId = userRepository.findByLoginId(userDTO.getLogin_id());
        if (userByLoginId.isPresent()) {
            throw new APIError("ExistsId", "이미 존재하는 아이디 입니다.");
        }

        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(userDTO.getPhone_number());
        if (userByPhoneNumber.isPresent()) {
            throw new APIError("ExistsPhoneNumber", "이미 존재하는 연락처 입니다.");
        }

        Optional<User> userByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (userByEmail.isPresent()) {
            throw new APIError("ExistsEmail", "이미 존재하는 이메일 입니다.");
        }
    }

    private static User mapper(UserDTO userDTO) {
        return new User(userDTO.getLogin_id(),
                EncryptUtils.sha256(userDTO.getPassword()),
                userDTO.getName(),
                userDTO.getPhone_number(),
                userDTO.getEmail(),
                Role.BASIC);
    }

    private void findByLoginIdValidate(String login_id) {
        if (login_id.isBlank()) {
            throw new APIError("Empty", "아이디를 입력해주세요.");
        }
        if (login_id.length() < 8) {
            throw new APIError("Length", "아이디를 8글자 이상 입력해주세요.");
        }
        if (userRepository.findByLoginId(login_id).isPresent()) {
            throw new APIError("Exist", "이미 존재하는 아이디 입니다.");
        }
    }

    private void findByPhoneNumberValidate(String phone_number) {
        if (phone_number.isBlank()) {
            throw new APIError("Empty", "연락처를 입력해주세요.");
        }
        if (userRepository.findByPhoneNumber(phone_number).isPresent()) {
            throw new APIError("Exist", "이미 존재하는 연락처 입니다.");
        }
    }

    private void findByEmailValidate(String email) {
        boolean email_validate = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);

        if (email.isBlank()) {
            throw new APIError("Empty", "이메일을 입력해주세요.");
        }
        if (!email_validate) {
            throw new APIError("Form", "이메일을 양식에 맞게 입력해주세요.");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new APIError("Exist", "이미 존재하는 이메일 입니다.");
        }
    }

    private void findByNameAndPhoneNumberValidate(String name, String phone_number) {
        if (name.isBlank()) {
            throw new APIError("EmptyName", "이름을 입력해주세요.");
        }
        if (phone_number.isBlank()) {
            throw new APIError("EmptyPhoneNumber", "연락처를 입력해주세요.");
        }
    }

    private void findByLoginIdAndNameAndPhoneNumberValidate(String login_id, String name, String phone_number) {
        if (login_id.isBlank()) {
            throw new APIError("EmptyLoginId", "아이디를 입력해주세요.");
        }
        if (login_id.length() < 8) {
            throw new APIError("Length", "아이디를 8글자 이상 입력해주세요.");
        }
        if (name.isBlank()) {
            throw new APIError("EmptyName", "이름을 입력해주세요.");
        }
        if (phone_number.isBlank()) {
            throw new APIError("EmptyPhoneNumber", "연락처를 입력해주세요.");
        }
    }

    private void updateByPasswordValidate(String login_id, String name, String phone_number, String password) {
        if (password.isBlank()) {
            throw new APIError("EmptyPassword", "비밀번호를 입력해주세요.");
        }
        User user = userRepository.findByLoginIdAndNameAndPhoneNumber(login_id, name, phone_number).orElse(null);
        user.updatePassword(password);
    }

    private void userUpdateValidate(String accessToken, String password, String phone_number, String email) {
        if (accessToken.isBlank()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }
        Optional<Token> token = Optional.ofNullable(tokenRepository.findByToken(accessToken));
        if (token.isEmpty()) {
            throw new APIError("NotLogin", "로그인 유저가 아닙니다.");
        }

        boolean password_validate = Pattern.matches("^(?=.*?[A-Z]+).{8,}", password);
        boolean email_validate = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);

        if (password.isBlank()) {
            throw new APIError("EmptyPassword", "비밀번호를 입력해주세요.");
        }
        if (password.length() < 8) {
            throw new APIError("LengthPassword", "비밀번호를 8글자 이상 입력해주세요.");
        }
        if (!password_validate) {
            throw new APIError("FormPassword", "비밀번호를 양식에 맞게 입력해주세요.");
        }
        if (phone_number.isBlank()) {
            throw new APIError("EmptyPhoneNumber", "연락처를 입력해주세요.");
        }
        if (email.isBlank()) {
            throw new APIError("EmptyEmail", "이메일을 입력해주세요.");
        }
        if (!email_validate) {
            throw new APIError("FormEmail", "이메일을 양식에 맞게 입력해주세요.");
        }

        User user = userRepository.findById(token.get().getUser_id()).orElse(null);
        User userByPhoneNumber = userRepository.findByPhoneNumber(phone_number).orElse(null);
        if (userRepository.findByPhoneNumber(phone_number).isPresent() &&
                !user.getPhoneNumber().equals(userByPhoneNumber.getPhoneNumber())) {
            throw new APIError("ExistPhoneNumber", "이미 존재하는 연락처입니다.");
        }

        User userByEmail = userRepository.findByEmail(email).orElse(null);
        if (userRepository.findByEmail(email).isPresent() &&
                !user.getEmail().equals(userByEmail.getEmail())) {
            throw new APIError("ExistsEmail", "이미 존재하는 이메일 입니다.");
        }

        user.setPassword(EncryptUtils.sha256(password));
        user.setPhoneNumber(phone_number);
        user.setEmail(email);

        userRepository.save(user);
    }

    private static String generateToken(String login_id) throws UnsupportedEncodingException {
        String val = String.valueOf((int) (Math.random() * 100000));
        String str = login_id + val;
        HexConverter hexConverter = new HexConverter();
        String stringToHex = hexConverter.getStringToHex(str);
        return stringToHex;
    }

}
