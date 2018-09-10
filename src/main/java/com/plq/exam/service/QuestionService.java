package com.plq.exam.service;

import com.plq.exam.model.entity.Question;
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
public interface QuestionService extends IService<Question> {

    List<Map<String,Object>> listQuestion(Integer examId);
}
