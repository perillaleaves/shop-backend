package perillaleaves.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.community.domain.User;
import perillaleaves.community.exception.APIError;
import perillaleaves.community.repository.UserRepository;
import perillaleaves.community.request.UserDTO;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserDTO userDTO) {
        validate(userDTO);

        LocalDateTime date = LocalDateTime.now();
        User user = mapper(userDTO);
        user.setCreatedAt(date);

        return userRepository.save(user);
    }

    public User findByLoginIdOrNull(String login_id) {
        if (login_id.isBlank()) {
            throw new APIError("EmptyLoginId", "아이디를 입력해주세요.");
        }
        if (login_id.length() < 8) {
            throw new APIError("LengthId", "아이디를 8글자 이상 입력해주세요.");
        }
        if (userRepository.findByLoginId(login_id).isPresent()) {
            throw new APIError("Exist", "이미 존재하는 아이디 입니다.");
        }

        return userRepository.findByLoginId(login_id).orElse(null);
    }

    public User findByPhoneNumberOrNull(String phone_number) {
        if (phone_number.isBlank()) {
            throw new APIError("EmptyPhoneNumber", "연락처를 입력해주세요.");
        }
        if (userRepository.findByPhoneNumber(phone_number).isPresent()) {
            throw new APIError("Exist", "이미 존재하는 연락처 입니다.");
        }

        return userRepository.findByPhoneNumber(phone_number).orElse(null);
    }


    private void validate(UserDTO userDTO) {
        boolean password_validate = Pattern.matches("^(?=.*?[A-Z]+).{8,}", userDTO.getPassword());
        boolean email_validate = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", userDTO.getEmail());

        if (userDTO.getLogin_id().isBlank()) {
            throw new APIError("EmptyName", "아이디를 입력해주세요.");
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
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getPhone_number(),
                userDTO.getEmail(),
                userDTO.getCreatedAt(),
                userDTO.getUpdatedAt());
    }

}
