package com.plq.exam.common.aop;

import com.alibaba.fastjson.JSON;
import com.plq.exam.common.entity.SysLog;
import com.plq.exam.common.service.SysLogService;
import com.plq.exam.common.utils.HttpContextUtil;
import com.plq.exam.common.utils.IPUtil;
import com.plq.exam.common.utils.UserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    private SysLogService sysLogService;

    @Autowired
    public SysLogAspect(SysLogService sysLogService){
        this.sysLogService = sysLogService;
    }

    @Pointcut("@annotation(com.plq.exam.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    public void saveSysLog(JoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        //注解上的描述
        com.plq.exam.common.annotation.SysLog syslog = method.getAnnotation(com.plq.exam.common.annotation.SysLog.class);
        if (syslog != null) {
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            String params = JSON.toJSONString(args[0]);
            sysLog.setParams(params);
        }


        //设置IP地址
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        sysLog.setIp(IPUtil.getIpAddr(request));

        //设置用户名
        String username = UserUtil.getUserName(request);
        sysLog.setUsername(username);

        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        //保存系统日志
        sysLogService.insert(sysLog);
    }


}
