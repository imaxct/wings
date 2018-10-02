package cn.sduonline.wings.exception;

/**
 * 学生信息不全 Created by imaxct on 18-10-2.
 */
public class InfoIncompleteException extends RuntimeException {
    public InfoIncompleteException(String message) {
        super(message);
    }
}
