package perillaleaves.community.request;

public class UserFindAndUpdatePasswordRequest {

    private String login_id;
    private String name;
    private String phone_number;

    private String password;
    private String re_password;

    public String getLogin_id() {
        return login_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public String getRe_password() {
        return re_password;
    }

    public UserFindAndUpdatePasswordRequest() {
    }

    public UserFindAndUpdatePasswordRequest(String login_id, String name, String phone_number, String password, String re_password) {
        this.login_id = login_id;
        this.name = name;
        this.phone_number = phone_number;
        this.password = password;
        this.re_password = re_password;
    }
}
