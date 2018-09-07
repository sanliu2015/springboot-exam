package com.plq.exam.service.impl;

import com.plq.exam.model.entity.Question;
import com.plq.exam.mapper.QuestionMapper;
import com.plq.exam.service.QuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author plq
 * @since 2018-09-03
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
