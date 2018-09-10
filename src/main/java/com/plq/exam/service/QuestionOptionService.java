package com.plq.exam.service;

import com.plq.exam.model.entity.QuestionOption;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author plq
 * @since 2018-09-03
 */
public interface QuestionOptionService extends IService<QuestionOption> {

    List<Map<String,Object>> listOption(Integer examId);
}
