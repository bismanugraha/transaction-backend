package com.transaction.nutech.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private int status;
    private String message;
    private T data;

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<>(200, "Success", data);
    }

    public static <T> GenericResponse<T> error(int status, String message) {
        return new GenericResponse<>(status, message, null);
    }

    public static <T> GenericResponse<T> of(HttpStatus status, String message, T data) {
        return new GenericResponse<>(status.value(), message, data);
    }
}
