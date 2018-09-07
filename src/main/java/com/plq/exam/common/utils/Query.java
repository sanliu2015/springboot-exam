package com.plq.exam.common.utils;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.ReflectionKit;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2017-03-14
 */
public class Query<T> extends Page<T> {
    private static final String PAGE = "page";
    private static final String SIZE = "size";
    private static final String ORDER_BY_FIELD = "orderByField";
    private static final String IS_ASC = "isAsc";

    public Query(Map<String, Object> params) {
        super(Integer.parseInt(params.getOrDefault(PAGE, 1).toString())
                , Integer.parseInt(params.getOrDefault(SIZE, 10).toString()));
        String orderByField = params.getOrDefault(ORDER_BY_FIELD, "") == null ? "" : params.getOrDefault(ORDER_BY_FIELD, "").toString();
        if (!StringUtils.isEmpty(orderByField)) {
            this.setOrderByField(orderByField);
        }
        Boolean isAsc = Boolean.parseBoolean(params.getOrDefault(IS_ASC, Boolean.TRUE) == null ? Boolean.TRUE.toString() : params.getOrDefault(IS_ASC, Boolean.TRUE).toString());
        this.setAsc(isAsc);
        params.remove(PAGE);
        params.remove(SIZE);
        params.remove(ORDER_BY_FIELD);
        params.remove(IS_ASC);
        this.setCondition(params);
    }

    public Query(Object t) {
        super(ReflectionKit.getMethodValue(t, "page") == null ? 1 : (Integer)ReflectionKit.getMethodValue(t, "page"),
                ReflectionKit.getMethodValue(t, "size") == null ? 10 : (Integer)ReflectionKit.getMethodValue(t, "size"));
        Object orderByFieldVal = ReflectionKit.getMethodValue(t, "orderByField");
        Object isAscVal = ReflectionKit.getMethodValue(t, "isAsc");
        if (orderByFieldVal != null && !"".equals(orderByFieldVal.toString())) {
            this.setOrderByField(orderByFieldVal.toString());
        }
        if (isAscVal == null || "".equals(isAscVal.toString())) {
            this.setAsc(true);
        } else {
            this.setAsc(Boolean.parseBoolean(isAscVal.toString()));
        }
    }

}
