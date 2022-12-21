package perillaleaves.community.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import perillaleaves.community.exception.APIError;
import perillaleaves.community.request.AdminDTO;
import perillaleaves.community.response.ErrorResponse;
import perillaleaves.community.response.Response;
import perillaleaves.community.response.ValidateResponse;
import perillaleaves.community.service.AdminService;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 1. 관리자 회원가입
    @PostMapping("/admin")
    public Response<ValidateResponse> signup(@RequestBody AdminDTO request) {
        try {
            adminService.save(request);
            return new Response<>(new ValidateResponse("signup", "등록 완료"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

}
