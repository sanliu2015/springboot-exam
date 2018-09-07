package com.plq.exam.common.exception;

import com.plq.exam.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 * 
 * @author plq
 * @email 717208317@qq.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResponseEntity<R> handleRRException(RRException e){
		R r = R.error(e.getCode(), e.getMsg());
		logger.error(e.getMessage(), e);
		return new ResponseEntity<>(r, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	/**
	 * 处理404页面
	 * @param e NoHandlerFoundException
	 * @return R
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<R> handleDefaultError(NoHandlerFoundException e) {
		logger.error("404异常:", e);
		return new ResponseEntity<>(R.error(404, "404异常"), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<R> handleException(Exception e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<>(R.error(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
