package perillaleaves.shop.response;

public class ValidateResponse {

    private final String code;

    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ValidateResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
