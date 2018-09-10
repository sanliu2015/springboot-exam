package com.plq.exam.mapper;

import com.plq.exam.model.entity.Question;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author plq
 * @since 2018-09-03
 */
public interface QuestionMapper extends BaseMapper<Question> {

    List<Map<String,Object>> listQuestion(Integer examId);
}
