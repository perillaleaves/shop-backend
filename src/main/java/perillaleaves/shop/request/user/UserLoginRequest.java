package perillaleaves.shop.request.user;

public class UserLoginRequest {

    private String login_id;
    private String password;

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginRequest() {
    }

    public UserLoginRequest(String login_id, String password) {
        this.login_id = login_id;
        this.password = password;
    }
}
