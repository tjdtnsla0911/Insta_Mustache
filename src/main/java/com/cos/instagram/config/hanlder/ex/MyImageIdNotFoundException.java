package com.cos.instagram.config.hanlder.ex;

public class MyImageIdNotFoundException extends RuntimeException{
	
	private String message;
	
	public MyImageIdNotFoundException() {
		System.out.println("config.hanlder.ex.MyImageIdNotFoundException.java의 MyImageIdNotFoundException에왔습니다");
		this.message = "해당 이미지의 id를 찾을 수 없습니다.";
	}
	
	public MyImageIdNotFoundException(String message) {	
		System.out.println("config.hanlder.ex.MyImageIdNotFoundException.java의 MyImageIdNotFoundException에왔습니다");
		System.out.println("config.hanlder.ex.MyImageIdNotFoundException.java의 MyImageIdNotFoundException의 message = "+message);

		this.message = message;
	}
	
	@Override
	public String getMessage() {
		System.out.println("config.hanlder.ex.MyImageIdNotFoundException.java의 getMessage에왔습니다");
		System.out.println("config.hanlder.ex.MyImageIdNotFoundException.java의 getMessage의 message = "+message);

		return message;
	}
	
}
