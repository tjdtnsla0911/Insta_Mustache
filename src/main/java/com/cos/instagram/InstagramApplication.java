package com.cos.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InstagramApplication {

	public static void main(String[] args) {
		System.out.println("인스타시작");
		SpringApplication.run(InstagramApplication.class, args);
		System.out.println("인스타 끝");
	}

}
