package perillaleaves.shop.response.user;

import java.time.LocalDateTime;

public class UserLoginResponse {

    private String login_id;
    private String name;
    private String phone_number;
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getLogin_id() {
        return login_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UserLoginResponse() {
    }

    public UserLoginResponse(String login_id, String name, String phone_number, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.login_id = login_id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
