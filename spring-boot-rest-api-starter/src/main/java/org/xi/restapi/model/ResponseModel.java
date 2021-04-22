package org.xi.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseModel<T> {

    private T data;
    private Collection<ResponseError> errors;

    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel<T>(data, null);
    }

    public static <T> ResponseModel<T> fail(Collection<ResponseError> errors) {
        return new ResponseModel<T>(null, errors);
    }
}
