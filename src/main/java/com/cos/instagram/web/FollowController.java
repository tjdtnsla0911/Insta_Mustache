package com.cos.instagram.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.dto.LoginUser;
import com.cos.instagram.domain.follow.Follow;
import com.cos.instagram.service.FollowService;
import com.cos.instagram.web.dto.FollowRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FollowController {

	private final FollowService followService;
	//follow가 아무도 없으면 일로온다
	@GetMapping("/follow/followingList/{pageUserId}")
	public String followingList(@PathVariable int pageUserId, @LoginUserAnnotation LoginUser loginUser, Model model) {
		model.addAttribute("followingList", followService.팔로잉리스트(loginUser.getId(), pageUserId));
		System.out.println("aaa");
		return "follow/following-list";
	}
	
	@GetMapping("/follow/followerList/{pageUserId}")
	public String followerList(@PathVariable int pageUserId, @LoginUserAnnotation LoginUser loginUser, Model model) {
		model.addAttribute("followerList", followService.팔로워리스트(loginUser.getId(), pageUserId));
		System.out.println("bbb");
		return "follow/follower-list";
	}
	
	@GetMapping("test/follow/followingList/{pageUserId}")
	public @ResponseBody List<FollowRespDto> testFollowingList(@PathVariable int pageUserId, @LoginUserAnnotation LoginUser loginUser, Model model) {
		
		return followService.팔로잉리스트(loginUser.getId(), pageUserId);
	}
	
	@PostMapping("/follow/{id}")
	public ResponseEntity<?> follow(@PathVariable int id,
			@LoginUserAnnotation LoginUser loginUser) {
		Integer result = followService.팔로우(loginUser.getId(), id);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/follow/{id}")
	public ResponseEntity<?> followGet(@PathVariable int id,
			@LoginUserAnnotation LoginUser loginUser) {
		Integer result = followService.getFollow(loginUser.getId(), id);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/follow/{id}")
	public ResponseEntity<?> unFollow(@PathVariable int id,
			@LoginUserAnnotation LoginUser loginUser) {
		
		followService.팔로우취소(loginUser.getId(), id);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	@PostMapping("/follow/getFollow")
	public ResponseEntity<?> getFollow(@LoginUserAnnotation LoginUser loginUser) {
		System.out.println("/follow/getFollow에 왔습니다.");
		
		List<Follow> list = followService.getFollowMember(loginUser.getId());
		System.out.println("list는 ? = "+list);
		if(list ==null || list.equals("")||list.size()==0) {
			System.out.println("list null 에왔습니다.");
			return new ResponseEntity<List<?>>(list,HttpStatus.OK);
		}
		System.out.println("list.get(0).getToUser().getId() = "+list.get(0).getToUser().getId());
		return new ResponseEntity<List<?>>(list, HttpStatus.OK); 
	}
}
