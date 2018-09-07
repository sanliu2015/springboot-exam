package com.plq.exam.common.utils;

import com.plq.exam.common.exception.RRException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidBindingResult {

    /**
     * 验证http请求参数
     * @param bindingResult
     */
    public static void valid(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder(100);
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (int i = 0, len = errorList.size(); i < len; i++) {
                if (i != 0) {
                    errors.append(",");
                }
                errors.append(errorList.get(i).getDefaultMessage());
            }
            throw new RRException(errors.toString());
        }
    }

}
