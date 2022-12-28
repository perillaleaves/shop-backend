package perillaleaves.shop.controller;

import org.springframework.web.bind.annotation.*;
import perillaleaves.shop.domain.user.User;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.request.user.*;
import perillaleaves.shop.response.ErrorResponse;
import perillaleaves.shop.response.Response;
import perillaleaves.shop.response.ValidateResponse;
import perillaleaves.shop.response.user.UserFindLoginIdResponse;
import perillaleaves.shop.response.user.UserLoginResponse;
import perillaleaves.shop.service.TokenService;
import perillaleaves.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    // 1.회원가입
    @PostMapping("/signup")
    public Response<ValidateResponse> signup(@RequestBody UserDTO request) {
        try {
            userService.save(request);
            return new Response<>(new ValidateResponse("signup", "회원가입 성공"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 2.아이디 중복 확인
    @GetMapping("/overlap/loginid")
    public Response<ValidateResponse> overlapByLoginId(@ModelAttribute UserOverlapLoginIdRequest request) {
        try {
            userService.findByLoginIdOrNull(request.getLogin_id());
            return new Response<>(new ValidateResponse("available", "사용 가능한 아이디 입니다."));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 3. 연락처 중복 확인
    @GetMapping("/overlap/phonenumber")
    public Response<ValidateResponse> overlapByPhoneNumber(@ModelAttribute UserOverlapPhoneNumberRequest request) {
        try {
            userService.findByPhoneNumberOrNull(request.getPhone_number());
            return new Response<>(new ValidateResponse("available", "사용 가능한 연락처 입니다."));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 4. 이메일 중복 확인
    @GetMapping("/overlap/email")
    public Response<ValidateResponse> overlapByEmail(@ModelAttribute UserOverlapEmailRequest request) {
        try {
            userService.findByEmailOrNull(request.getEmail());
            return new Response<>(new ValidateResponse("available", "사용 가능한 이메일 입니다."));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 5. 아이디 찾기
    @GetMapping("/loginid")
    public Response<UserFindLoginIdResponse> findLoginId(@ModelAttribute UserFindLoginIdRequest request) {
        try {
            User user = userService.findByNameAndPhoneNumber(request.getName(), request.getPhone_number());
            return new Response<>(new UserFindLoginIdResponse(user.getLoginId()));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 6. 비밀번호 찾기(검증 및 조회)
    @GetMapping("/password")
    public Response<ValidateResponse> findByLoginIdAndNameAndPhoneNumber(@ModelAttribute UserFindAndUpdatePasswordRequest request) {
        try {
            User user = userService.findByLoginIdAndNameAndPhoneNumber(request.getLogin_id(), request.getName(), request.getPhone_number());
            if (user != null) {
                return new Response<>(new ValidateResponse("success", "검증 및 조회 성공"));
            }
            return new Response<>(new ValidateResponse("fail", "검증 및 조회 실패"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 7. 비밀번호 변경(검증 및 조회 성공 시)
    @PutMapping("/password")
    public Response<ValidateResponse> updateByPassword(@RequestBody UserFindAndUpdatePasswordRequest request) {
        try {
            userService.updateByPassword(request.getLogin_id(), request.getName(), request.getPhone_number(), request.getPassword(), request.getRe_password());
            return new Response<>(new ValidateResponse("success", "비밀번호 변경 "));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    // 8. 로그인
    @PostMapping("/login")
    public Response<ValidateResponse> login(@RequestBody UserLoginRequest loginRequest, HttpServletResponse response) {
        try {
            String token = userService.login(loginRequest.getLogin_id(), loginRequest.getPassword());
            response.setHeader("token", token);
            return new Response<>(new ValidateResponse("login", "로그인"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        } catch (UnsupportedEncodingException ue) {
            throw new RuntimeException(ue);
        }
    }

    // 9. 로그아웃
    @PostMapping("/logout")
    public Response<ValidateResponse> logout(HttpServletRequest request) {
        try {
            tokenService.deleteToken(request);
            request.removeAttribute("token");

            return new Response<>(new ValidateResponse("logout", "로그아웃"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }

    }

    // 10. 내 정보 보기
    @GetMapping("/user/{user_id}")
    public Response<UserLoginResponse> myPage(@PathVariable("user_id") Long user_id, HttpServletRequest request) {
        try {
            User user = userService.myUserInformation(user_id, request);
            return new Response<>(new UserLoginResponse(user.getLoginId(),
                    user.getName(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }

    }
}

