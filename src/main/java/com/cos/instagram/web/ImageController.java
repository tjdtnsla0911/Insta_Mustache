package com.cos.instagram.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.dto.LoginUser;
import com.cos.instagram.domain.image.Image;
import com.cos.instagram.service.ImageService;
import com.cos.instagram.web.dto.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;
	
	@GetMapping({"", "/", "/image/feed"})
	public String feed(
			String tag,
			@LoginUserAnnotation LoginUser loginUser,
			Model model) {
		System.out.println("web.ImageController.java의 경로 '/'에 왔습니다");
		System.out.println("loginUser : "+loginUser);
		model.addAttribute("images", imageService.피드사진(loginUser.getId(), tag));
		//System.out.println("대체 모델이 뭘 가져 가는건가  ? ="+imageService.피드사진(loginUser.getId(), tag));
		System.out.println("login이 가지고 있는 유저는 ? ="+loginUser.getUser().getProfileImage());
	
		return "image/feed";
	}
	
	@GetMapping("/test/image/feed")
	public @ResponseBody List<Image> testFeed(
			String tag,
			@LoginUserAnnotation LoginUser loginUser) {
		System.out.println("web.ImageController.java의 경로 '/test/image/feed'에 왔습니다");
		return imageService.피드사진(loginUser.getId(), tag);
	}
	
	@GetMapping("/image/uploadForm")
	public String imageUploadForm() {
		System.out.println("web.ImageController.java의 경로 '/image/uploadForm'에 왔습니다");

		return "image/image-upload";
	}
	
	@PostMapping("/image")
	public String imageUpload(
			@LoginUserAnnotation LoginUser loginUser,
			ImageReqDto imageReqDto) {
		System.out.println("web.ImageController.java의 경로 '/image'에 왔습니다");

		imageService.사진업로드(imageReqDto, loginUser.getId());
		
		return "redirect:/user/"+loginUser.getId();
	}
	
	@GetMapping("/image/explore")
	public String imageExplore(@LoginUserAnnotation LoginUser loginUser, Model model) {
		model.addAttribute("images", imageService.인기사진(loginUser.getId()));
		System.out.println("web.ImageController.java의 경로 '/image/explore'에 왔습니다");

		return "image/explore";
		
	}
}




