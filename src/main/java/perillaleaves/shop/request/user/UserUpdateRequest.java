package perillaleaves.shop.request.user;

public class UserUpdateRequest {

    private String accessToken;
    private String password;
    private String phone_number;
    private String email;

    public String getAccessToken() {
        return accessToken;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }
}
