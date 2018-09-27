package cn.sduonline.wings.exception;

/**
 * Created by imaxct on 18-9-26.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }
}
