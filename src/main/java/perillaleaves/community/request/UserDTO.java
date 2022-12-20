package perillaleaves.community.request;

import java.time.LocalDateTime;

public class UserDTO {

    private String loginId;

    private String password;

    private String name;

    private String phone_number;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getLogin_id() {
        return loginId;
    }

    public String getPassword() {
        return password;
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


}
