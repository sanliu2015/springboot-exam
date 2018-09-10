package com.plq.exam.service;

import com.baomidou.mybatisplus.service.IService;
import com.plq.exam.model.entity.Exam;
import com.plq.exam.model.entity.ExamineeExam;

import java.util.List;
import java.util.Map;

public interface ExamService extends IService<Exam> {
    List<Map<String,Object>> listQuestionAndOption(Integer examId);

    void examineeSubmit(ExamineeExam examineeExam);

    void insertExam(List<List<Object>> dataList, Integer examId);
}
