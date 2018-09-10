package com.plq.exam.controller;

import com.plq.exam.common.exception.RRException;
import com.plq.exam.common.utils.R;
import com.plq.exam.model.dto.ExamineeLoginDto;
import com.plq.exam.model.entity.Examinee;
import com.plq.exam.model.entity.ExamineeExam;
import com.plq.exam.service.ExamService;
import com.plq.exam.service.ExamineeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {

    @Autowired
    private HttpServletRequest request;
    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExamService examService;
    @Autowired
    private ExamineeService examineeService;

    @RequestMapping("/exam/{id}")
    public String index(@PathVariable Integer id) {
        return "exam/detail";
    }

    @PostMapping("/loginByExaminee")
    @ResponseBody
    public R loginByExaminee(@RequestBody ExamineeLoginDto examineeLoginDto) {
        Examinee examinee = new Examinee();
        BeanUtils.copyProperties(examineeLoginDto, examinee);
        examineeService.insert(examinee);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("examinee", examinee);
        List<Map<String, Object>> qustionList = examService.listQuestionAndOption(examineeLoginDto.getExamId());
        resultMap.put("questions", qustionList);
        return R.ok().put("result", resultMap);
    }

    @PostMapping("/exam/examineeSubmit")
    @ResponseBody
    public R examineeSubmit(@RequestBody ExamineeExam examineeExam) {
        examService.examineeSubmit(examineeExam);
        examineeExam.setSubmitTime(new Date());
        return R.ok();
    }

    @PostMapping("/exam/{examId}/initData")
    @ResponseBody
    public R init(@RequestBody List<List<Object>> dataList, @PathVariable Integer examId) {
        examService.insertExam(dataList, examId);
        return R.ok();
    }


}
