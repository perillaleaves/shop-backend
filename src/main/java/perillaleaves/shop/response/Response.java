package perillaleaves.community.response;

public class Response<T> {

    private T data;

    private ValidateResponse validate;

    private ErrorResponse error;

    public T getData() {
        return data;
    }

    public ValidateResponse getValidate() {
        return validate;
    }

    public ErrorResponse getError() {
        return error;
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(T data, ValidateResponse validate) {
        this.data = data;
        this.validate = validate;
    }

    public Response(ValidateResponse validate) {
        this.validate = validate;
    }

    public Response(ErrorResponse error) {
        this.error = error;
    }
}
