package com.cos.instagram.config.hanlder;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.instagram.config.hanlder.ex.MyImageIdNotFoundException;
import com.cos.instagram.config.hanlder.ex.MyUserIdNotFoundException;
import com.cos.instagram.config.hanlder.ex.MyUsernameNotFoundException;
import com.cos.instagram.util.Script;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=MyUserIdNotFoundException.class)
	public String myUserIdNotFoundException(Exception e) {
		System.out.println("config.hanlder.GlobalExceptionHandler.java의  myUserIdNotFoundException에 왔습니다ㄴ");
		return Script.back(e.getMessage());
	}
	
	@ExceptionHandler(value=MyUsernameNotFoundException.class)
	public String myUsernameNotFoundException(Exception e) {
		System.out.println("config.hanlder.GlobalExceptionHandler.java의  myUsernameNotFoundException에 왔습니다ㄴ");

		return Script.alert(e.getMessage());
	}
	
	@ExceptionHandler(value=MyImageIdNotFoundException.class)
	public String myImageIdNotFoundException(Exception e) {
		System.out.println("config.hanlder.GlobalExceptionHandler.java의  myImageIdNotFoundException에 왔습니다ㄴ");

		return Script.alert(e.getMessage());
	}
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String myIllegalArgumentException(Exception e) {
		System.out.println("config.hanlder.GlobalExceptionHandler.java의  myIllegalArgumentException에 왔습니다ㄴ");

		return Script.alert(e.getMessage());
	}
}
