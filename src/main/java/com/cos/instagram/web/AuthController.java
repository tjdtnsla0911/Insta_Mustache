package com.cos.instagram.web;

import javax.servlet.http.HttpSession;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.instagram.config.auth.PrincipalDetails;
import com.cos.instagram.config.auth.PrincipalDetailsService;
import com.cos.instagram.domain.user.UserRepository;
import com.cos.instagram.service.UserService;
import com.cos.instagram.web.dto.JoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	private final UserRepository userRepository;
	private final HttpSession session;
     
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private final UserService userService;
	@PostMapping("/auth/login")
	public  String asdf(String username,String password) {
		System.out.println("새롼든 login2로 왔나요");
		System.out.println("받은 username = "+username);
		System.out.println("받은 password = "+password);
		return "ㅅㅂ";
		
	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {

		
		System.out.println("/auth/loginForm에 왔습니다");
		log.info("/auth/loginForm 진입"); 
		return "auth/loginForm"; 
	}
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		log.info("/auth/joinForm 진입"); 
		return "auth/joinForm";
	}
	@PostMapping("/auth/join")
	public String join(JoinReqDto joinReqDto) {
		System.out.println("/auth/join에 왔습니다.");


		log.info(joinReqDto.toString());
		userService.회원가입(joinReqDto);
		return "redirect:/auth/loginForm";
	}
}



