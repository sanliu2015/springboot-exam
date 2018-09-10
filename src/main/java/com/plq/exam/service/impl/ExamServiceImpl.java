package com.plq.exam.service.impl;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.plq.exam.mapper.ExamMapper;
import com.plq.exam.model.entity.Exam;
import com.plq.exam.model.entity.ExamineeExam;
import com.plq.exam.model.entity.Question;
import com.plq.exam.model.entity.QuestionOption;
import com.plq.exam.service.ExamService;
import com.plq.exam.service.QuestionOptionService;
import com.plq.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionOptionService questionOptionService;

    @Override
    @Cacheable(value = "examCache", key = "#examId")
    public List<Map<String, Object>> listQuestionAndOption(Integer examId) {

        List<Map<String, Object>> questionList = questionService.listQuestion(examId);
        List<Map<String, Object>> qtOptionList = questionOptionService.listOption(examId);
        questionList.forEach(q -> {
            List<Map<String, Object>> optionList = qtOptionList.stream()
                    .filter(m -> m.get("questionId").toString().equals(q.get("questionId").toString()))
                    .collect(Collectors.toList());
            q.put("options", optionList);
        });
        return questionList;
    }

    @Override
    public void examineeSubmit(ExamineeExam examineeExam) {
        this.baseMapper.examineeSubmit(examineeExam);
    }

    @Override
    public void insertExam(List<List<Object>> dataList, Integer examId) {
        dataList.forEach(objects -> {
            Question question = new Question();
            question.setExamId(examId);
            question.setQuestionContent(objects.get(0).toString());
            question.setQuestionScore(new BigDecimal(objects.get(6).toString()));
            question.setAnswerAnalysis(objects.get(8).toString());
            questionService.insert(question);
            String ans = this.changeToDigit(objects.get(7).toString());
            for (int i=1; i<=5; i++) {
                if (!StringUtils.isEmpty(objects.get(i))) {
                    QuestionOption questionOption = new QuestionOption();
                    questionOption.setQuestionId(question.getId());
                    questionOption.setOptionContent(objects.get(i).toString());
                    if (ans.contains(i + "")) {
                        questionOption.setIsAnswer(1);
                    }
                    questionOptionService.insert(questionOption);
                }
            }

        });
    }

    private String changeToDigit(String ans) {
        char[] chars = ans.toCharArray();
        StringBuilder res = new StringBuilder();
        for (char c : chars) {
            res.append(c-64);  // A为65
        }
        return res.toString();
    }
}
