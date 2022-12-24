package perillaleaves.shop.request.user;

public class UserOverlapLoginIdRequest {

    private final String login_id;

    public String getLogin_id() {
        return login_id;
    }

    public UserOverlapLoginIdRequest(String login_id) {
        this.login_id = login_id;
    }
}
