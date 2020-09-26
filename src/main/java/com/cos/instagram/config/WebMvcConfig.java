package com.cos.instagram.config;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.dto.LoginUser;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer{
	
	private final HttpSession httpSession;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		System.out.println("cofig.WebMvcConfig.java의 addArgumentResolvers에 진입");

		resolvers.add(new HandlerMethodArgumentResolver() {

			// 1. supportsParameter() 에서 true가 리턴되면!!
			@Override
			public boolean supportsParameter(MethodParameter parameter) {
				System.out.println("cofig.WebMvcConfig.java의 supportsParameter에 진입");
				boolean isLoginUserAnnotation = 
						parameter.getParameterAnnotation(LoginUserAnnotation.class) != null;
				boolean isUserClass = 
						LoginUser.class.equals(parameter.getParameterType());
				return isLoginUserAnnotation && isUserClass;
			}
			
			// 2. 세션을 만들어서 @LoginUserAnnotation 에 주입해준다.
			@Override
			public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
					NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
System.out.println("cofig.WebMvcConfig.java의 resolveArgument에 진입");
System.out.println("cofig.WebMvcConfig.java의 resolveArgument에 진입 전의 값 = " +httpSession.getAttribute("loginUser"));
System.out.println("cofig.WebMvcConfig.java의 resolveArgument에 진입전값 뺴고 나서 ㄹㅇ 진입전");				
return httpSession.getAttribute("loginUser");
			}
		});
	}
	
	// 이미지 경로 찾기를 위해 추가 시작
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("cofig.SecurityConfig.java의 addResourceHandlers에 진입");
		System.out.println("cofig.SecurityConfig.java의 addResourceHandlers의 registry= "+registry);

		WebMvcConfigurer.super.addResourceHandlers(registry);
	
		registry
			.addResourceHandler("/upload/**")
			.addResourceLocations("file:///" + uploadFolder)
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
	}
	// 이미지 경로 찾기를 위해 추가 끝
}
