package sample.wooni.place.controller.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResultResponse<T> {
    private final boolean success;
    private final int code;
    private final String message;
    private final T data;

    @Builder
    public ResultResponse(boolean success,
                          int code,
                          String message,
                          T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultResponse<T> ok(T data) {
        return new ResultResponse<>(true, HttpStatus.OK.value(), null, data);
    }

    public static <T> ResultResponse<T> failed(int code, String message) {
        return new ResultResponse<>(false, code, message, null);
    }
}
