package sample.wooni.place.controller.advice;

import sample.wooni.place.controller.common.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultResponse<String> validMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResultResponse.failed(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResultResponse<String> validIllegalArgumentException(IllegalArgumentException e) {
        return ResultResponse.failed(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResultResponse<String> validException(Exception e) {
        log.error("Failed {}", e.getMessage(), e);
        return ResultResponse.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("잠시후 다시 시도해주세요, %s", e.getMessage()));
    }
}
