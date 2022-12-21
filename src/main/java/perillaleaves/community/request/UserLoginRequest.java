package perillaleaves.community.request;

public class UserLoginRequest {

    private final String login_id;
    private final String password;

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginRequest(String login_id, String password) {
        this.login_id = login_id;
        this.password = password;
    }
}
