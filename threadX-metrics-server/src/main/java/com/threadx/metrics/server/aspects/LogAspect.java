package com.threadx.metrics.server.aspects;

import com.threadx.metrics.server.common.annotations.Log;
import com.threadx.metrics.server.common.context.LoginContext;
import com.threadx.metrics.server.constant.LogConstant;
import com.threadx.metrics.server.dto.RequestData;
import com.threadx.metrics.server.entity.ActiveLog;
import com.threadx.metrics.server.enums.LogEnum;
import com.threadx.metrics.server.init.LogMessageConsumer;
import com.threadx.metrics.server.vo.UserVo;
import com.threadx.utils.ThreadXThrowableMessageUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * *************************************************<br/>
 * 日志切面<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/6/3 23:15
 */
@Aspect
@Component
public class LogAspect {
    @Around("@annotation(com.threadx.metrics.server.common.annotations.Log)")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        ActiveLog activeLog = new ActiveLog();
        activeLog.init();
        long startTime = System.currentTimeMillis();
        activeLog.setStartTime(startTime);
        Object result;
        try {
            result = joinPoint.proceed();
            activeLog.setResultState(LogConstant.SUCCESS);
        } catch (Throwable throwable) {
            // 在这里可以获取到错误信息并进行处理
            String errorMessage = ThreadXThrowableMessageUtil.messageRead(throwable);
            activeLog.setErrorMessage(errorMessage);
            activeLog.setResultState(LogConstant.ERROR);
            throw throwable;
        } finally {
            long endTime = System.currentTimeMillis();
            activeLog.setEndTime(endTime);
            //获取任务耗时
            long executionTime = endTime - startTime;
            activeLog.setOperationTime(executionTime);
            // 获取方法上的@Log注解信息
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Log logAnnotation = method.getDeclaredAnnotation(Log.class);


            // 获取注解信息并进行处理
            if (logAnnotation != null) {
                LogEnum log = logAnnotation.value();
                activeLog.setActiveKey(log.getActiveKey());
                activeLog.setActiveLog(log.getLogMessage());
            }


            UserVo userData = LoginContext.getUserData();
            if (userData != null) {
                Long id = userData.getId();
                if (id != null) {
                    activeLog.setUserId(id);
                }
            }

            RequestData requestData = LoginContext.getRequestData();
            activeLog.setBrowser(requestData.getBrowser());
            activeLog.setOs(requestData.getOs());
            activeLog.setIpAddress(requestData.getIpAddress());

            LogMessageConsumer.addLog(activeLog);
        }

        return result;
    }
}
