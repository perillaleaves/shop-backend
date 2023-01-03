package perillaleaves.shop.request.user;

import perillaleaves.shop.domain.enumList.Role;

public class UserDTO {

    private String login_id;

    private String password;

    private String name;

    private String phone_number;

    private String email;

    private Role role;


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

    public UserDTO(String login_id, String password, String name, String phone_number, String email, Role role) {
        this.login_id = login_id;
        this.password = password;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.role = role;
    }
}
