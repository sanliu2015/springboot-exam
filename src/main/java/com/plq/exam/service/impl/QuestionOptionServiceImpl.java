package com.plq.exam.service.impl;

import com.plq.exam.model.entity.QuestionOption;
import com.plq.exam.mapper.QuestionOptionMapper;
import com.plq.exam.service.QuestionOptionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author plq
 * @since 2018-09-03
 */
@Service
public class QuestionOptionServiceImpl extends ServiceImpl<QuestionOptionMapper, QuestionOption> implements QuestionOptionService {
    @Override
    public List<Map<String, Object>> listOption(Integer examId) {
        return this.baseMapper.listOption(examId);
    }
}
