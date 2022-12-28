package perillaleaves.shop.response.user;

import perillaleaves.shop.common.BaseEntity;

public class UserLoginResponse extends BaseEntity {

    private String login_id;
    private String name;
    private String phone_number;
    private String email;

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

    public UserLoginResponse() {
    }

    public UserLoginResponse(String login_id, String name, String phone_number, String email) {
        this.login_id = login_id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
    }
}
