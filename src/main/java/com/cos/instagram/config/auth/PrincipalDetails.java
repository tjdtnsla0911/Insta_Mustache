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
		System.out.println("PrincipalDetails에 왔습니다");
		System.out.println("user = "+user);


		if(user == null ) {

	
		res.setContentType("text/html; charset=UTF-8");

			PrintWriter script = null;
			try {
				script = res.getWriter();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("여기로왔음 비번틀린곳");
			script.println("<script>alert('비번이 틀렸습니다')</script>");
			script.println("location.href=/");
		}
		this.user = user;
	}
	
	// OAuth 로그인 용 생성자
	public PrincipalDetails(User user, Map<String, Object> attributes) {


		
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {


		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(()-> user.getRole().getKey());

		return collection;
	}

	@Override
	public String getPassword() {
System.out.println("getPassword에 왔습니다 + user.getpassword = "+user.getPassword());

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
