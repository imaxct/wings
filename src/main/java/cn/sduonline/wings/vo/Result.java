package cn.sduonline.wings.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by imaxct on 18-9-26.
 */
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {
    private boolean ok;
    private String msg;
    private T data;

    public static <T> Result ok(T data) {
        return new Result<>(true, null, data);
    }

    public static Result err(String msg, Object data) {
        return new Result<>(false, msg, data);
    }
}
