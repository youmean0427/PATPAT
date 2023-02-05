package com.ssafy.patpat.common.error;

import javax.servlet.ServletException;

public class LogoutException extends ServletException {
    public LogoutException(String msg){
        super(msg);
    }
}
