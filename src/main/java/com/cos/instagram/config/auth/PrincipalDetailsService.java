package com.cos.instagram.config.auth;



import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.instagram.config.ConfigReturn;
import com.cos.instagram.config.auth.dto.LoginUser;
import com.cos.instagram.domain.user.HakkerRepository;
import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;
import com.cos.instagram.domain.user.Haker;


import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service

public class PrincipalDetailsService implements UserDetailsService,OAuth2User {

	
	private static final Logger log = LoggerFactory.getLogger(PrincipalDetailsService.class);
	private static final org.springframework.security.core.Authentication Authentication = null;
	private final UserRepository userRepository;
//	
	@Autowired
	HakkerRepository hakerRepository;
	 Haker haker;

	private final HttpSession session;
	
	// Security Session > Authentication > UserDetails
	// 해당 함수가 정상적으로 리턴되면 @AuthenticationPricipal 어노테이션 활성화됨.
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    List<Haker> hekerList = hakerRepository.findAll();
	    InetAddress ips = null;
		try {
			ips = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    String myIp = ips.getHostAddress().toString();
	    System.out.println("myIp= "+myIp);
	    System.out.println("hakerList = "+hekerList);
	    for(int i=0; i<=hekerList.size()-1;i++) {
	    	if(myIp == hekerList.get(i).getIp()||
	    			myIp.equals(hekerList.get(i).getIp())) {
	    		System.out.println("if문에왔습니다");
	    		System.out.println("myIp = "+myIp);
	    		System.out.println("hekerList = "+hekerList.get(i).getIp());
	    		HttpServletResponse response = null;
	    
	    		response.setContentType("text/html; charset=UTF-8");
	    		PrintWriter out;
				try {
					
					out = response.getWriter();
					out.println("<script>alert('로그인 정보를 확인해주세요.'); history.go(-1);</script>");
		    		out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	
//				ConfigReturn co = new ConfigReturn();
//				co.boan();

	 
	    	

	    	
	    
	    	
	    	}
	    	
	    }
	    System.out.println("hakerList = "+hekerList);

		
		System.out.println("받은 username = " + username);

		if (username.contains("<sc") || username.contains("</") || username.contains("alert()")) {
			Haker haker2 = new Haker();
			System.out.println("유저의 아이디안에는 스크립트공격이 있을확률이 있습니다.");
			String attack = "스크립트공격";
			try {
			
				
				InetAddress ipss = InetAddress.getLocalHost(); // 여기서 ip잡고
				String ip = ipss.getHostAddress().toString();//
				System.out.println("ip = "+ip);
				System.out.println("Host Name = [" + ips.getHostName() + "]");
				System.out.println("Host Address = [" + ips.getHostAddress() + "]");
				haker2.setIp(ip);
				haker2.setAttack(attack);
				System.out.println("해커 = "+haker2.getAttack());
				System.out.println("해커 = "+haker2.getIp());
				hakerRepository.save(haker2);
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

	private void script(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
