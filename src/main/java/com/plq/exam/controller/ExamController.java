package com.plq.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExamController {

    @Autowired
    private HttpServletRequest request;
    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/exam/{id}")
    public String index(@PathVariable Integer id) {
        return "exam/detail";
    }

}
