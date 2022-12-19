package perillaleaves.community.response;

public class Response<T> {

    private T data;

    private ValidateResponse validateResponse;

    private ErrorResponse errorResponse;

    public T getData() {
        return data;
    }

    public ValidateResponse getValidateResponse() {
        return validateResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(ValidateResponse validateResponse) {
        this.validateResponse = validateResponse;
    }

    public Response(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
