package com.cos.instagram.config.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.instagram.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
	HttpServletResponse res = null ;

	private static final long serialVersionUID = 1L;

	private User user;
	private Map<String, Object> attributes;
	
	// 일반 로그인 용 생성자
	public PrincipalDetails(User user) {
		System.out.println("config.auth.PrincipalDetails.java의 PrincipalDetails에 진입 ");
		System.out.println("config.auth.PrincipalDetails.java의 PrincipalDetails의 user = "+user);
		if(user == null ) {
			System.out.println("usernull에 왔습니다");
	
		res.setContentType("text/html; charset=UTF-8");
		System.out.println("script하러 직전 까지왔습니다.");
			PrintWriter script = null;
			try {
				script = res.getWriter();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			script.println("<script>alert('비번이 틀렸습니다')</script>");
			script.println("location.href=/");
		}
		this.user = user;
	}
	
	// OAuth 로그인 용 생성자
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		System.out.println("config.auth.PrincipalDetails.java의 PrincipalDetails의 진입(오버로딩)");
		System.out.println("config.auth.PrincipalDetails.java의 PrincipalDetails의 진입(오버로딩)의 user = "+user);
		
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("config.auth.PrincipalDetails.java의 getAuthorities에 진입");

		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(()-> user.getRole().getKey());
		System.out.println("config.auth.PrincipalDetails.java의 getAuthorities의 reutrn 직전 collection ="+collection);
		return collection;
	}

	@Override
	public String getPassword() {
		System.out.println("config.auth.PrincipalDetails.java의 getPassword에 진입");
		System.out.println("config.auth.PrincipalDetails.java의 getPassword의 user.getPassword = "+user.getPassword());
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return user.getProviderId();
	}
}
