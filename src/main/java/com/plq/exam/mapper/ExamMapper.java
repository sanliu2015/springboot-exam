package com.plq.exam.mapper;

import com.plq.exam.model.entity.Exam;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.plq.exam.model.entity.ExamineeExam;

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
public interface ExamMapper extends BaseMapper<Exam> {

    List<Map<String,Object>> listQuestionAndOption(Integer examId);

    void examineeSubmit(ExamineeExam examineeExam);
}
