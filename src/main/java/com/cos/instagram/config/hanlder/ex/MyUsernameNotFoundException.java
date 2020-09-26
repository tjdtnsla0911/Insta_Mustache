package com.cos.instagram.config.hanlder.ex;

public class MyUsernameNotFoundException extends RuntimeException{
	
	private String message;
	
	public MyUsernameNotFoundException() {
		System.out.println("config.hanlder.ex.MyUsernameNotFoundException.java의 MyUsernameNotFoundException에 왔습니다");
		System.out.println("config.hanlder.ex.MyUsernameNotFoundException.java의 MyUsernameNotFoundException의 this.massge = "+this.message);;
		this.message = "해당 유저네임을 찾을 수 없습니다.";
	}
	
	public MyUsernameNotFoundException(String message) {
		System.out.println("config.hanlder.ex.MyUsernameNotFoundException.java의 MyUsernameNotFoundException에 왔습니다");
		System.out.println("config.hanlder.ex.MyUsernameNotFoundException.java의 MyUsernameNotFoundException의 message ="+message);

		this.message = message;
	}
	
	@Override
	public String getMessage() {
		System.out.println("config.hanlder.ex.MyUsernameNotFoundException.java의 getMessage의 message ="+message);

		return message;
	}
}
