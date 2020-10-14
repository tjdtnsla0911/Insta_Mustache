package com.cos.instagram.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.cos.instagram.config.oauth.PrincipalOAuth2UserService;
import com.cos.instagram.domain.user.Haker;
import com.cos.instagram.domain.user.HakkerRepository;
import com.cos.instagram.util.Script;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
 
	@Autowired
	private PrincipalOAuth2UserService principalOAuth2UserService;
	@Autowired
	HakkerRepository hakerRepository;
	 Haker haker;
	@Bean
	public BCryptPasswordEncoder encode() {
		System.out.println("BCryptPasswordEncoder에 왔습니다.");

		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/", "/user/**", "/follow/**", "/image/**") //특정한 경로 지정
		.authenticated()
		.antMatchers("/admin/**")
		.access("hasRole('ROLE_ADMIN')")
		.anyRequest()
		.permitAll()
		.and()
		.formLogin()
		.loginPage("/auth/loginForm")//앞으로 로그인은 이경로에서 수행한다
		.loginProcessingUrl("/auth/login")
		.defaultSuccessUrl("/")
		.failureHandler(new AuthenticationFailureHandler() {		
			@Override 
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				System.out.println("onAuthenticationFailure에옴");
//////////////
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
			    
			    		response.setContentType("text/html; charset=UTF-8");
			    		PrintWriter out;
						try {
							
							out = response.getWriter();
							out.print(Script.back2("이 Ip는 "+hekerList.get(i).getAttack()+"의 전력이있으므로 차단중입니다."));
				    		out.flush();
				    		return;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	
//						ConfigReturn co = new ConfigReturn();
//						co.boan();

			 
			    	

			    	
			    
			    	
			    	}
			    	
			    }
				
				////
				response.setContentType("text/html; charset=utf-8"); 
				PrintWriter out = response.getWriter();
				out.print(Script.back("유저네임 혹은 비밀번호를 찾을 수 없습니다."));
				return;
			}
		})
		.and()
		.logout()
		.logoutUrl("/auth/logout")
		.logoutSuccessUrl("/auth/loginForm")
		.and()
		.oauth2Login()  // oauth 요청 주소가 다 활성화
		.userInfoEndpoint() //  oauth 로그인 성공 이후 사용자 정보를 가져오기위한 설정을 담당
		.userService(principalOAuth2UserService); // 담당할 서비스를 등록한다. (로그인 후 후처리 되는 곳)
	}
}







