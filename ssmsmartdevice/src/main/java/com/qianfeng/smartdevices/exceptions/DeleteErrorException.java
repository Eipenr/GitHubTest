package com.qianfeng.smartdevices.exceptions;

public class DeleteErrorException extends MybaseException{
    public DeleteErrorException(String message, int code) {
        super(message, code);
    }
}
