package com.cos.instagram.config.hanlder.ex;

public class MyUserIdNotFoundException extends RuntimeException{
	
	private String message;
	
	public MyUserIdNotFoundException() {
		System.out.println("config.hanlder.ex.MyUserIdNotFoundException.java의 MyUserIdNotFoundException애 진입 ");
		this.message = "해당 유저의 id를 찾을 수 없습니다.";
	}
	
	public MyUserIdNotFoundException(String message) {
		System.out.println("config.hanlder.ex.MyUserIdNotFoundException.java의 MyUserIdNotFoundException애 진입 ");
		System.out.println("config.hanlder.ex.MyUserIdNotFoundException.java의 MyUserIdNotFoundException의 message ="+message);

		this.message = message;
	}
	
	@Override
	public String getMessage() {
		System.out.println("config.hanlder.ex.MyUserIdNotFoundException.java의 getMessage에 진입");
		System.out.println("config.hanlder.ex.MyUserIdNotFoundException.java의 getMessage의 message = "+message);

		return message;
	}
	
}
