package org.example.baozi.recyclingbook.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<T>(200, "success", data);
    }
    public static <T> ResponseMessage<T> success() {
        return new ResponseMessage<T>(200, "success", null);
    }
    public static <T> ResponseMessage<T> success(int code,String message) {
        return new ResponseMessage<T>(code, message, null);
    }
    public static <T> ResponseMessage<T> error(String message) {
        return new ResponseMessage<T>(500, message, null);
    }
    public static <T> ResponseMessage<T> info(String message) {
        return new ResponseMessage<T>(400, message, null);
    }
    public static <T> ResponseMessage<T> info(int code,String message) {
        return new ResponseMessage<T>(code, message, null);
    }
}
