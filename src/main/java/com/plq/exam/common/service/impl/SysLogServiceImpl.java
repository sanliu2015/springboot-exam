package com.plq.exam.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.plq.exam.common.entity.SysLog;
import com.plq.exam.common.mapper.SysLogMapper;
import com.plq.exam.common.service.SysLogService;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
