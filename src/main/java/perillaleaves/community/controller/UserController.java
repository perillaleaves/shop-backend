package perillaleaves.community.controller;

import org.springframework.web.bind.annotation.*;
import perillaleaves.community.domain.User;
import perillaleaves.community.exception.APIError;
import perillaleaves.community.request.*;
import perillaleaves.community.response.ErrorResponse;
import perillaleaves.community.response.Response;
import perillaleaves.community.response.UserFindLoginIdResponse;
import perillaleaves.community.response.ValidateResponse;
import perillaleaves.community.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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


}
