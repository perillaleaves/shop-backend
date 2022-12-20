package perillaleaves.community.request;

public class UserOverlapLoginIdRequest {

    private final String login_id;

    public String getLogin_id() {
        return login_id;
    }

    public UserOverlapLoginIdRequest(String login_id) {
        this.login_id = login_id;
    }
}
