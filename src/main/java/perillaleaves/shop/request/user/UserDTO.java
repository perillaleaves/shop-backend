package perillaleaves.shop.request.user;

import perillaleaves.shop.domain.Role;

import java.time.LocalDateTime;

public class UserDTO {

    private String login_id;

    private String password;

    private String name;

    private String phone_number;

    private String email;

    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getLogin_id() {
        return login_id;
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

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


}
