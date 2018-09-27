package cn.sduonline.wings.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by imaxct on 18-9-27.
 */
@Data
@AllArgsConstructor
public class ErrorDetail {
    private boolean ok;
    private String msg;
    private Object data;
}
