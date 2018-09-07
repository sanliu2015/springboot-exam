package com.plq.exam.common.utils;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    public static String getUserName(HttpServletRequest request) {
        return request.getParameter("userName");
    }
}
