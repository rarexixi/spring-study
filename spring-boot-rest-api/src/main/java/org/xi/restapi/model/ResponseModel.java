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

    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel<T>(data);
    }
}
