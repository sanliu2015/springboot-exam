package com.plq.exam.service.impl;

import com.plq.exam.model.entity.Examinee;
import com.plq.exam.mapper.ExamineeMapper;
import com.plq.exam.model.entity.Question;
import com.plq.exam.service.ExamineeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.plq.exam.service.QuestionOptionService;
import com.plq.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 考生信息 服务实现类
 * </p>
 *
 * @author plq
 * @since 2018-09-03
 */
@Service
public class ExamineeServiceImpl extends ServiceImpl<ExamineeMapper, Examinee> implements ExamineeService {

}
