package perillaleaves.community.request.user;

public class UserOverlapPhoneNumberRequest {

    private final String phone_number;

    public String getPhone_number() {
        return phone_number;
    }

    public UserOverlapPhoneNumberRequest(String phone_number) {
        this.phone_number = phone_number;
    }
}
