package com.plq.exam.service.impl;

import com.plq.exam.model.entity.Examinee;
import com.plq.exam.mapper.ExamineeMapper;
import com.plq.exam.service.ExamineeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
