package com.cos.instagram.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.dto.LoginUser;
import com.cos.instagram.domain.image.Image;
import com.cos.instagram.domain.user.Haker;
import com.cos.instagram.domain.user.HakkerRepository;
import com.cos.instagram.service.ImageService;
import com.cos.instagram.service.UserService;
import com.cos.instagram.util.Script;
import com.cos.instagram.web.dto.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;
	private final UserService userService;
	Haker haker;
	@Autowired
	HakkerRepository hakerRepository;
	
	
	
	
	@GetMapping({"", "/", "/image/feed"})
	public String feed(
			String tag,
			@LoginUserAnnotation LoginUser loginUser,
			Model model,Model model2,HttpServletResponse response) {
		System.out.println("web.ImageConroller.java의 '' , '/','image/feed' 에왔습니다");
		//model2.addAllAttributes("userlists",userService)
		///////추가할곳 /////////
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
			    	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	
//					ConfigReturn co = new ConfigReturn();
//					co.boan();

		 
		    	

		    	
		    
		    	
		    	}
		    	
		    }
		
		////////////////////////////////////
		model2.addAttribute("myNumber",loginUser.getId());
		model.addAttribute("images", imageService.피드사진(loginUser.getId(), tag));
		System.out.println("요거슨 로그인 유저의 ID여  ="+loginUser.getId());
	
		return "image/feed";
	}
	
	@GetMapping("/test/image/feed")
	public @ResponseBody List<Image> testFeed(
			String tag,
			@LoginUserAnnotation LoginUser loginUser) {
		
		return imageService.피드사진(loginUser.getId(), tag);
	}
	
	@GetMapping("/image/uploadForm")
	public String imageUploadForm() {


		return "image/image-upload";
	}
	
	@PostMapping("/image")
	public String imageUpload(
			@LoginUserAnnotation LoginUser loginUser,
			ImageReqDto imageReqDto) {


		imageService.사진업로드(imageReqDto, loginUser.getId());
		
		return "redirect:/user/"+loginUser.getId();
	}
	
	@GetMapping("/image/explore")
	public String imageExplore(@LoginUserAnnotation LoginUser loginUser, Model model) {
		model.addAttribute("images", imageService.인기사진(loginUser.getId()));


		return "image/explore";
		
	}
}




