package com.cos.instagram.config.auth;

import java.net.InetAddress;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.instagram.config.auth.dto.LoginUser;
import com.cos.instagram.domain.user.HakkerRepository;
import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;
import com.cos.instagram.domain.user.Haker;


import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service

public class PrincipalDetailsService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(PrincipalDetailsService.class);
	private static final org.springframework.security.core.Authentication Authentication = null;
	private final UserRepository userRepository;
//	
	@Autowired
	HakkerRepository hakerRepository;

	private final HttpSession session;
	
	// Security Session > Authentication > UserDetails
	// 해당 함수가 정상적으로 리턴되면 @AuthenticationPricipal 어노테이션 활성화됨.
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Haker haker = new Haker();
		System.out.println("loadUserByUsername에 왔습니다");
		
		System.out.println("받은 username = " + username);

		if (username.contains("<sc") || username.contains("</") || username.contains("alert()")) {
			System.out.println("유저의 아이디안에는 스크립트공격이 있을확률이 있습니다.");
			String attack = "스크립트공격";
			try {
				
				InetAddress ips = InetAddress.getLocalHost(); // 여기서 ip잡고
				String ip = ips.getHostAddress().toString();//
				System.out.println("ip = "+ip);
				System.out.println("Host Name = [" + ips.getHostName() + "]");
				System.out.println("Host Address = [" + ips.getHostAddress() + "]");
				haker.setIp(ip);
				haker.setAttack(attack);
				System.out.println("해커 = "+haker.getAttack());
				System.out.println("해커 = "+haker.getIp());
				hakerRepository.save(haker);
//				hakerRepository2.save(haker);
//				hakerRepository.save(haker);
			
			} catch (Exception e) {
				System.out.println(e);
			}

		}

		log.info("loadUserByUsername : username : " + username);

		User userEntity = userRepository.findByUsername(username).map(new Function<User, User>() {
			
			@Override
			public User apply(User t) {
				System.out.println("t = "+t);
				session.setAttribute("loginUser", new LoginUser(t));
				return t;
			}
		}).orElse(null);
System.out.println("여긴왔는가 userEntity? ="+userEntity);
		return new PrincipalDetails(userEntity);
	}

}
